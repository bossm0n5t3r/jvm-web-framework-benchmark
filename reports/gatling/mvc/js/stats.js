var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "10000",
        "ok": "8862",
        "ko": "1138"
    },
    "minResponseTime": {
        "total": "10000",
        "ok": "15059",
        "ko": "10000"
    },
    "maxResponseTime": {
        "total": "25989",
        "ok": "25989",
        "ko": "10060"
    },
    "meanResponseTime": {
        "total": "15469",
        "ok": "16170",
        "ko": "10008"
    },
    "standardDeviation": {
        "total": "2968",
        "ok": "2370",
        "ko": "8"
    },
    "percentiles1": {
        "total": "15502",
        "ok": "15522",
        "ko": "10005"
    },
    "percentiles2": {
        "total": "15583",
        "ok": "15593",
        "ko": "10009"
    },
    "percentiles3": {
        "total": "22317",
        "ok": "23087",
        "ko": "10025"
    },
    "percentiles4": {
        "total": "25712",
        "ok": "25806",
        "ko": "10048"
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
    "count": 8862,
    "percentage": 88.62
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 1138,
    "percentage": 11.379999999999999
},
    "meanNumberOfRequestsPerSecond": {
        "total": "277.78",
        "ok": "246.17",
        "ko": "31.61"
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
        "ok": "8862",
        "ko": "1138"
    },
    "minResponseTime": {
        "total": "10000",
        "ok": "15059",
        "ko": "10000"
    },
    "maxResponseTime": {
        "total": "25989",
        "ok": "25989",
        "ko": "10060"
    },
    "meanResponseTime": {
        "total": "15469",
        "ok": "16170",
        "ko": "10008"
    },
    "standardDeviation": {
        "total": "2968",
        "ok": "2370",
        "ko": "8"
    },
    "percentiles1": {
        "total": "15502",
        "ok": "15522",
        "ko": "10005"
    },
    "percentiles2": {
        "total": "15583",
        "ok": "15593",
        "ko": "10009"
    },
    "percentiles3": {
        "total": "22363",
        "ok": "23069",
        "ko": "10024"
    },
    "percentiles4": {
        "total": "25663",
        "ok": "25798",
        "ko": "10048"
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
    "count": 8862,
    "percentage": 88.62
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 1138,
    "percentage": 11.379999999999999
},
    "meanNumberOfRequestsPerSecond": {
        "total": "277.78",
        "ok": "246.17",
        "ko": "31.61"
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
