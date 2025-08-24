var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "10000",
        "ok": "10000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "419",
        "ok": "419",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "5032",
        "ok": "5032",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "4007",
        "ok": "4007",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1172",
        "ok": "1172",
        "ko": "-"
    },
    "percentiles1": {
        "total": "4498",
        "ok": "4498",
        "ko": "-"
    },
    "percentiles2": {
        "total": "5001",
        "ok": "5001",
        "ko": "-"
    },
    "percentiles3": {
        "total": "5002",
        "ok": "5002",
        "ko": "-"
    },
    "percentiles4": {
        "total": "5013",
        "ok": "5013",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 51,
    "percentage": 0.51
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 118,
    "percentage": 1.18
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 9831,
    "percentage": 98.31
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "666.67",
        "ok": "666.67",
        "ko": "-"
    }
},
contents: {
"req_-mvc--external---728483527": {
        type: "REQUEST",
        name: "[MVC] External API with no database",
path: "[MVC] External API with no database",
pathFormatted: "req_-mvc--external---728483527",
stats: {
    "name": "[MVC] External API with no database",
    "numberOfRequests": {
        "total": "10000",
        "ok": "10000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "419",
        "ok": "419",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "5032",
        "ok": "5032",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "4007",
        "ok": "4007",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1172",
        "ok": "1172",
        "ko": "-"
    },
    "percentiles1": {
        "total": "4498",
        "ok": "4498",
        "ko": "-"
    },
    "percentiles2": {
        "total": "5001",
        "ok": "5001",
        "ko": "-"
    },
    "percentiles3": {
        "total": "5002",
        "ok": "5002",
        "ko": "-"
    },
    "percentiles4": {
        "total": "5013",
        "ok": "5013",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 51,
    "percentage": 0.51
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 118,
    "percentage": 1.18
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 9831,
    "percentage": 98.31
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "666.67",
        "ok": "666.67",
        "ko": "-"
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
