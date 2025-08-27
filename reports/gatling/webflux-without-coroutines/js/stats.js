var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "10000",
        "ok": "5294",
        "ko": "4706"
    },
    "minResponseTime": {
        "total": "298",
        "ok": "298",
        "ko": "10001"
    },
    "maxResponseTime": {
        "total": "24397",
        "ok": "24397",
        "ko": "10198"
    },
    "meanResponseTime": {
        "total": "8768",
        "ok": "7661",
        "ko": "10014"
    },
    "standardDeviation": {
        "total": "2964",
        "ok": "3741",
        "ko": "24"
    },
    "percentiles1": {
        "total": "10001",
        "ok": "6491",
        "ko": "10004"
    },
    "percentiles2": {
        "total": "10007",
        "ok": "7923",
        "ko": "10011"
    },
    "percentiles3": {
        "total": "13509",
        "ok": "16588",
        "ko": "10069"
    },
    "percentiles4": {
        "total": "19869",
        "ok": "19639",
        "ko": "10120"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 7,
    "percentage": 0.06999999999999999
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 141,
    "percentage": 1.41
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 5146,
    "percentage": 51.459999999999994
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 4706,
    "percentage": 47.06
},
    "meanNumberOfRequestsPerSecond": {
        "total": "303.03",
        "ok": "160.42",
        "ko": "142.61"
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
        "ok": "5294",
        "ko": "4706"
    },
    "minResponseTime": {
        "total": "298",
        "ok": "298",
        "ko": "10001"
    },
    "maxResponseTime": {
        "total": "24397",
        "ok": "24397",
        "ko": "10198"
    },
    "meanResponseTime": {
        "total": "8768",
        "ok": "7661",
        "ko": "10014"
    },
    "standardDeviation": {
        "total": "2964",
        "ok": "3741",
        "ko": "24"
    },
    "percentiles1": {
        "total": "10001",
        "ok": "6490",
        "ko": "10004"
    },
    "percentiles2": {
        "total": "10007",
        "ok": "7924",
        "ko": "10011"
    },
    "percentiles3": {
        "total": "13925",
        "ok": "16589",
        "ko": "10069"
    },
    "percentiles4": {
        "total": "19761",
        "ok": "19552",
        "ko": "10118"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 7,
    "percentage": 0.06999999999999999
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 141,
    "percentage": 1.41
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 5146,
    "percentage": 51.459999999999994
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 4706,
    "percentage": 47.06
},
    "meanNumberOfRequestsPerSecond": {
        "total": "303.03",
        "ok": "160.42",
        "ko": "142.61"
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
