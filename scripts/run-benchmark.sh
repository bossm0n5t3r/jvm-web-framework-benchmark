#!/usr/bin/env bash
#
# Runs one Gatling simulation against freshly started app processes with fixed JVM options.
#
# Usage:
#   scripts/run-benchmark.sh <target> [-- <extra app args>]
#
# Targets:
#   mvc | mvc-without-vt | webflux | webflux-without-coroutines | ktor | external-app
#
# Examples:
#   scripts/run-benchmark.sh mvc
#   scripts/run-benchmark.sh mvc-without-vt -- --benchmark.platform-thread-pool-size=1000 --server.tomcat.threads.max=334
#
# Environment:
#   JVM_OPTS    JVM options for every app process
#               (default: -Xms4g -Xmx4g -XX:+UseG1GC -XX:+ExitOnOutOfMemoryError)
#   SKIP_BUILD  set to 1 to reuse the jars that are already built
#
# Each run starts external-app (and the target app) as new JVMs and stops them on exit,
# so repeated runs do not share JIT state or connections. Logs go to build/benchmark-runs/.

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT_DIR"

# ExitOnOutOfMemoryError stops a broken JVM right away instead of letting it keep answering requests.
JVM_OPTS="${JVM_OPTS:--Xms4g -Xmx4g -XX:+UseG1GC -XX:+ExitOnOutOfMemoryError}"
REQUIRED_JAVA_MAJOR=25
READY_TIMEOUT_SECONDS=120
SHUTDOWN_TIMEOUT_SECONDS=30
SIMULATION_PACKAGE="me.bossm0n5t3r.benchmark"

EXTERNAL_APP_PORT=8082

usage() {
    sed -n '3,21p' "$0" | sed 's/^# \{0,1\}//'
    exit 1
}

log() {
    echo "[run-benchmark] $*"
}

[[ $# -ge 1 ]] || usage
TARGET="$1"
shift
if [[ $# -gt 0 ]]; then
    [[ "$1" == "--" ]] || usage
    shift
fi
APP_ARGS=("$@")

# target -> app module, port, simulation class
case "$TARGET" in
mvc)
    APP_MODULE="mvc-app"
    APP_PORT=8080
    SIMULATION="MVCSimulation"
    ;;
mvc-without-vt)
    APP_MODULE="mvc-without-virtual-thread-app"
    APP_PORT=8083
    SIMULATION="MVCWithoutVirtualThreadSimulation"
    ;;
webflux)
    APP_MODULE="webflux-app"
    APP_PORT=8081
    SIMULATION="WebFluxSimulation"
    ;;
webflux-without-coroutines)
    APP_MODULE="webflux-app"
    APP_PORT=8081
    SIMULATION="WebFluxWithoutCoroutinesSimulation"
    ;;
ktor)
    APP_MODULE="ktor-app"
    APP_PORT=8084
    SIMULATION="KtorSimulation"
    ;;
external-app)
    APP_MODULE=""
    APP_PORT=""
    SIMULATION="ExternalAppSimulation"
    ;;
*) usage ;;
esac

jar_task() {
    if [[ "$1" == "ktor-app" ]]; then echo ":ktor-app:buildFatJar"; else echo ":$1:bootJar"; fi
}

jar_path() {
    if [[ "$1" == "ktor-app" ]]; then
        echo "ktor-app/build/libs/ktor-app-all.jar"
    else
        find "$1/build/libs" -name "$1-*.jar" ! -name "*-plain.jar" 2>/dev/null | head -n 1 || true
    fi
}

check_java() {
    local java_bin="${JAVA_HOME:+$JAVA_HOME/bin/}java"
    local major
    major="$("$java_bin" -XshowSettings:properties -version 2>&1 | awk -F'= ' '/java.specification.version/ {print $2}')"
    if [[ "$major" != "$REQUIRED_JAVA_MAJOR" ]]; then
        log "Java $REQUIRED_JAVA_MAJOR is required, but $java_bin is Java $major. Set JAVA_HOME to a JDK $REQUIRED_JAVA_MAJOR."
        exit 1
    fi
    JAVA_BIN="$java_bin"
}

check_port_free() {
    if nc -z localhost "$1" 2>/dev/null; then
        log "Port $1 is already in use. Stop the process listening on it first."
        exit 1
    fi
}

wait_for_port() {
    local name="$1" port="$2" pid="$3"
    local waited=0
    until nc -z localhost "$port" 2>/dev/null; do
        if ! kill -0 "$pid" 2>/dev/null; then
            log "$name exited before listening on port $port. See $RUN_DIR/$name.log"
            exit 1
        fi
        if ((waited >= READY_TIMEOUT_SECONDS)); then
            log "$name did not listen on port $port within ${READY_TIMEOUT_SECONDS}s. See $RUN_DIR/$name.log"
            exit 1
        fi
        sleep 1
        waited=$((waited + 1))
    done
    log "$name is listening on port $port (${waited}s)"
}

PIDS=()

# A JVM that hit OutOfMemoryError may not handle SIGTERM, so fall back to SIGKILL after a grace period.
# shellcheck disable=SC2329 # invoked indirectly via `trap cleanup EXIT`
cleanup() {
    local pid waited
    for pid in "${PIDS[@]+"${PIDS[@]}"}"; do
        kill "$pid" 2>/dev/null || true
    done
    for pid in "${PIDS[@]+"${PIDS[@]}"}"; do
        waited=0
        while kill -0 "$pid" 2>/dev/null && ((waited < SHUTDOWN_TIMEOUT_SECONDS)); do
            sleep 1
            waited=$((waited + 1))
        done
        if kill -0 "$pid" 2>/dev/null; then
            log "Process $pid did not stop within ${SHUTDOWN_TIMEOUT_SECONDS}s, killing it"
            kill -9 "$pid" 2>/dev/null || true
        fi
        wait "$pid" 2>/dev/null || true
    done
}
trap cleanup EXIT

start_app() {
    local module="$1" port="$2"
    shift 2
    local jar
    jar="$(jar_path "$module")"
    [[ -f "$jar" ]] || {
        log "Jar for $module not found. Run without SKIP_BUILD=1."
        exit 1
    }
    # shellcheck disable=SC2086 # JVM_OPTS is intentionally split into separate options
    "$JAVA_BIN" $JVM_OPTS -jar "$jar" "$@" >"$RUN_DIR/$module.log" 2>&1 &
    PIDS+=("$!")
    wait_for_port "$module" "$port" "$!"
}

check_java

RUN_DIR="build/benchmark-runs/$(date +%Y%m%d-%H%M%S)-$TARGET"
mkdir -p "$RUN_DIR"
log "Target: $TARGET ($SIMULATION), JVM_OPTS: $JVM_OPTS, logs: $RUN_DIR"

check_port_free "$EXTERNAL_APP_PORT"
[[ -z "$APP_MODULE" ]] || check_port_free "$APP_PORT"

if [[ "${SKIP_BUILD:-0}" != "1" ]]; then
    tasks=("$(jar_task external-app)")
    [[ -z "$APP_MODULE" ]] || tasks+=("$(jar_task "$APP_MODULE")")
    log "Building ${tasks[*]}"
    ./gradlew -q "${tasks[@]}"
fi

if [[ -n "$APP_MODULE" ]]; then
    # Jar runs do not include spring-boot-docker-compose (developmentOnly), so start PostgreSQL here.
    log "Starting PostgreSQL"
    docker compose up -d --wait postgres
fi

start_app external-app "$EXTERNAL_APP_PORT"
[[ -z "$APP_MODULE" ]] || start_app "$APP_MODULE" "$APP_PORT" "${APP_ARGS[@]+"${APP_ARGS[@]}"}"

log "Running $SIMULATION"
set +e
./gradlew :benchmark-app:gatlingRun --non-interactive --simulation "$SIMULATION_PACKAGE.$SIMULATION" \
    2>&1 | tee "$RUN_DIR/gatling.log"
gatling_status="${PIPESTATUS[0]}"
set -e

if [[ "$gatling_status" -eq 0 ]]; then
    log "Done. All assertions passed."
else
    log "Gatling exited with status $gatling_status (failed assertions also return non-zero). See $RUN_DIR/gatling.log"
fi
exit "$gatling_status"
