var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "10000",
        "ok": "7225",
        "ko": "2775"
    },
    "minResponseTime": {
        "total": "2552",
        "ok": "5493",
        "ko": "2552"
    },
    "maxResponseTime": {
        "total": "19658",
        "ok": "18719",
        "ko": "19658"
    },
    "meanResponseTime": {
        "total": "9598",
        "ok": "9267",
        "ko": "10460"
    },
    "standardDeviation": {
        "total": "2576",
        "ok": "2824",
        "ko": "1457"
    },
    "percentiles1": {
        "total": "10005",
        "ok": "8477",
        "ko": "10020"
    },
    "percentiles2": {
        "total": "11060",
        "ok": "11122",
        "ko": "10672"
    },
    "percentiles3": {
        "total": "14145",
        "ok": "14978",
        "ko": "13129"
    },
    "percentiles4": {
        "total": "16287",
        "ok": "16394",
        "ko": "16701"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 0,
    "percentage": 0.0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 0,
    "percentage": 0.0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 7225,
    "percentage": 72.25
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 2775,
    "percentage": 27.750000000000004
},
    "meanNumberOfRequestsPerSecond": {
        "total": "384.62",
        "ok": "277.88",
        "ko": "106.73"
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
        "ok": "7225",
        "ko": "2775"
    },
    "minResponseTime": {
        "total": "2552",
        "ok": "5493",
        "ko": "2552"
    },
    "maxResponseTime": {
        "total": "19658",
        "ok": "18719",
        "ko": "19658"
    },
    "meanResponseTime": {
        "total": "9598",
        "ok": "9267",
        "ko": "10460"
    },
    "standardDeviation": {
        "total": "2576",
        "ok": "2824",
        "ko": "1457"
    },
    "percentiles1": {
        "total": "10001",
        "ok": "8477",
        "ko": "10020"
    },
    "percentiles2": {
        "total": "11058",
        "ok": "11122",
        "ko": "10674"
    },
    "percentiles3": {
        "total": "14155",
        "ok": "14979",
        "ko": "13129"
    },
    "percentiles4": {
        "total": "16349",
        "ok": "16391",
        "ko": "16701"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 0,
    "percentage": 0.0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 0,
    "percentage": 0.0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 7225,
    "percentage": 72.25
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 2775,
    "percentage": 27.750000000000004
},
    "meanNumberOfRequestsPerSecond": {
        "total": "384.62",
        "ok": "277.88",
        "ko": "106.73"
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
