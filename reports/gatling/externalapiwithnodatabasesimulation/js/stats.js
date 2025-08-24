var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "2000",
        "ok": "2000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "5146",
        "ok": "5146",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "8448",
        "ok": "8448",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "5668",
        "ok": "5668",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "257",
        "ok": "257",
        "ko": "-"
    },
    "percentiles1": {
        "total": "5646",
        "ok": "5646",
        "ko": "-"
    },
    "percentiles2": {
        "total": "5741",
        "ok": "5739",
        "ko": "-"
    },
    "percentiles3": {
        "total": "6102",
        "ok": "6098",
        "ko": "-"
    },
    "percentiles4": {
        "total": "6512",
        "ok": "6513",
        "ko": "-"
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
    "count": 2000,
    "percentage": 100.0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "222.22",
        "ok": "222.22",
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
        "total": "1000",
        "ok": "1000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "5224",
        "ok": "5224",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "8448",
        "ok": "8448",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "5574",
        "ok": "5574",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "161",
        "ok": "161",
        "ko": "-"
    },
    "percentiles1": {
        "total": "5598",
        "ok": "5598",
        "ko": "-"
    },
    "percentiles2": {
        "total": "5666",
        "ok": "5666",
        "ko": "-"
    },
    "percentiles3": {
        "total": "5729",
        "ok": "5730",
        "ko": "-"
    },
    "percentiles4": {
        "total": "5767",
        "ok": "5767",
        "ko": "-"
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
    "count": 1000,
    "percentage": 100.0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "111.11",
        "ok": "111.11",
        "ko": "-"
    }
}
    },"req_-webflux--exter-43474902": {
        type: "REQUEST",
        name: "[WebFlux] External API with no database",
path: "[WebFlux] External API with no database",
pathFormatted: "req_-webflux--exter-43474902",
stats: {
    "name": "[WebFlux] External API with no database",
    "numberOfRequests": {
        "total": "1000",
        "ok": "1000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "5146",
        "ok": "5146",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "6562",
        "ok": "6562",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "5762",
        "ok": "5762",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "297",
        "ok": "297",
        "ko": "-"
    },
    "percentiles1": {
        "total": "5724",
        "ok": "5724",
        "ko": "-"
    },
    "percentiles2": {
        "total": "5970",
        "ok": "5971",
        "ko": "-"
    },
    "percentiles3": {
        "total": "6423",
        "ok": "6418",
        "ko": "-"
    },
    "percentiles4": {
        "total": "6527",
        "ok": "6521",
        "ko": "-"
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
    "count": 1000,
    "percentage": 100.0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "111.11",
        "ok": "111.11",
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
