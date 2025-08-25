var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "10000",
        "ok": "8714",
        "ko": "1286"
    },
    "minResponseTime": {
        "total": "10000",
        "ok": "15137",
        "ko": "10000"
    },
    "maxResponseTime": {
        "total": "25868",
        "ok": "25868",
        "ko": "10053"
    },
    "meanResponseTime": {
        "total": "15186",
        "ok": "15950",
        "ko": "10004"
    },
    "standardDeviation": {
        "total": "2688",
        "ok": "1935",
        "ko": "4"
    },
    "percentiles1": {
        "total": "15471",
        "ok": "15493",
        "ko": "10003"
    },
    "percentiles2": {
        "total": "15528",
        "ok": "15534",
        "ko": "10005"
    },
    "percentiles3": {
        "total": "17415",
        "ok": "23330",
        "ko": "10010"
    },
    "percentiles4": {
        "total": "23809",
        "ok": "23680",
        "ko": "10025"
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
    "count": 8714,
    "percentage": 87.14
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 1286,
    "percentage": 12.86
},
    "meanNumberOfRequestsPerSecond": {
        "total": "277.78",
        "ok": "242.06",
        "ko": "35.72"
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
        "ok": "8714",
        "ko": "1286"
    },
    "minResponseTime": {
        "total": "10000",
        "ok": "15137",
        "ko": "10000"
    },
    "maxResponseTime": {
        "total": "25868",
        "ok": "25868",
        "ko": "10053"
    },
    "meanResponseTime": {
        "total": "15186",
        "ok": "15950",
        "ko": "10004"
    },
    "standardDeviation": {
        "total": "2688",
        "ok": "1935",
        "ko": "4"
    },
    "percentiles1": {
        "total": "15471",
        "ok": "15493",
        "ko": "10003"
    },
    "percentiles2": {
        "total": "15528",
        "ok": "15534",
        "ko": "10005"
    },
    "percentiles3": {
        "total": "17310",
        "ok": "23329",
        "ko": "10010"
    },
    "percentiles4": {
        "total": "23781",
        "ok": "23679",
        "ko": "10025"
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
    "count": 8714,
    "percentage": 87.14
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 1286,
    "percentage": 12.86
},
    "meanNumberOfRequestsPerSecond": {
        "total": "277.78",
        "ok": "242.06",
        "ko": "35.72"
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
