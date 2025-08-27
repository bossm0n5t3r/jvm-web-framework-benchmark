var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "10000",
        "ok": "7653",
        "ko": "2347"
    },
    "minResponseTime": {
        "total": "219",
        "ok": "219",
        "ko": "10001"
    },
    "maxResponseTime": {
        "total": "16388",
        "ok": "16388",
        "ko": "10222"
    },
    "meanResponseTime": {
        "total": "9453",
        "ok": "9278",
        "ko": "10022"
    },
    "standardDeviation": {
        "total": "3090",
        "ok": "3513",
        "ko": "29"
    },
    "percentiles1": {
        "total": "10003",
        "ok": "8296",
        "ko": "10008"
    },
    "percentiles2": {
        "total": "11465",
        "ok": "11898",
        "ko": "10029"
    },
    "percentiles3": {
        "total": "14543",
        "ok": "14595",
        "ko": "10080"
    },
    "percentiles4": {
        "total": "14805",
        "ok": "14877",
        "ko": "10140"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 78,
    "percentage": 0.7799999999999999
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 50,
    "percentage": 0.5
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 7525,
    "percentage": 75.25
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 2347,
    "percentage": 23.47
},
    "meanNumberOfRequestsPerSecond": {
        "total": "416.67",
        "ok": "318.88",
        "ko": "97.79"
    }
},
contents: {
"req_-webflux--exter-43474902": {
        type: "REQUEST",
        name: "[WebFlux] External API with no database",
path: "[WebFlux] External API with no database",
pathFormatted: "req_-webflux--exter-43474902",
stats: {
    "name": "[WebFlux] External API with no database",
    "numberOfRequests": {
        "total": "10000",
        "ok": "7653",
        "ko": "2347"
    },
    "minResponseTime": {
        "total": "219",
        "ok": "219",
        "ko": "10001"
    },
    "maxResponseTime": {
        "total": "16388",
        "ok": "16388",
        "ko": "10222"
    },
    "meanResponseTime": {
        "total": "9453",
        "ok": "9278",
        "ko": "10022"
    },
    "standardDeviation": {
        "total": "3090",
        "ok": "3513",
        "ko": "29"
    },
    "percentiles1": {
        "total": "10001",
        "ok": "8295",
        "ko": "10008"
    },
    "percentiles2": {
        "total": "11457",
        "ok": "11903",
        "ko": "10029"
    },
    "percentiles3": {
        "total": "14538",
        "ok": "14598",
        "ko": "10080"
    },
    "percentiles4": {
        "total": "14784",
        "ok": "14887",
        "ko": "10140"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 78,
    "percentage": 0.7799999999999999
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 50,
    "percentage": 0.5
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 7525,
    "percentage": 75.25
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 2347,
    "percentage": 23.47
},
    "meanNumberOfRequestsPerSecond": {
        "total": "416.67",
        "ok": "318.88",
        "ko": "97.79"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
