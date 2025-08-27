var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "10000",
        "ok": "637",
        "ko": "9363"
    },
    "minResponseTime": {
        "total": "15352",
        "ok": "15352",
        "ko": "17781"
    },
    "maxResponseTime": {
        "total": "60006",
        "ok": "60000",
        "ko": "60006"
    },
    "meanResponseTime": {
        "total": "51018",
        "ok": "32059",
        "ko": "52308"
    },
    "standardDeviation": {
        "total": "16879",
        "ok": "13664",
        "ko": "16293"
    },
    "percentiles1": {
        "total": "60001",
        "ok": "30343",
        "ko": "60001"
    },
    "percentiles2": {
        "total": "60002",
        "ok": "45181",
        "ko": "60002"
    },
    "percentiles3": {
        "total": "60002",
        "ok": "59971",
        "ko": "60002"
    },
    "percentiles4": {
        "total": "60002",
        "ok": "59993",
        "ko": "60002"
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
    "count": 637,
    "percentage": 6.370000000000001
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 9363,
    "percentage": 93.63
},
    "meanNumberOfRequestsPerSecond": {
        "total": "142.86",
        "ok": "9.1",
        "ko": "133.76"
    }
},
contents: {
"req_-mvc-without-vi--1011174272": {
        type: "REQUEST",
        name: "[MVC without Virtual Thread] External API with no database",
path: "[MVC without Virtual Thread] External API with no database",
pathFormatted: "req_-mvc-without-vi--1011174272",
stats: {
    "name": "[MVC without Virtual Thread] External API with no database",
    "numberOfRequests": {
        "total": "10000",
        "ok": "637",
        "ko": "9363"
    },
    "minResponseTime": {
        "total": "15352",
        "ok": "15352",
        "ko": "17781"
    },
    "maxResponseTime": {
        "total": "60006",
        "ok": "60000",
        "ko": "60006"
    },
    "meanResponseTime": {
        "total": "51018",
        "ok": "32059",
        "ko": "52308"
    },
    "standardDeviation": {
        "total": "16879",
        "ok": "13664",
        "ko": "16293"
    },
    "percentiles1": {
        "total": "60001",
        "ok": "30344",
        "ko": "60001"
    },
    "percentiles2": {
        "total": "60002",
        "ok": "45182",
        "ko": "60002"
    },
    "percentiles3": {
        "total": "60002",
        "ok": "59971",
        "ko": "60002"
    },
    "percentiles4": {
        "total": "60003",
        "ok": "59992",
        "ko": "60002"
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
    "count": 637,
    "percentage": 6.370000000000001
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 9363,
    "percentage": 93.63
},
    "meanNumberOfRequestsPerSecond": {
        "total": "142.86",
        "ok": "9.1",
        "ko": "133.76"
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
