var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "2588",
        "ok": "880",
        "ko": "1708"
    },
    "minResponseTime": {
        "total": "23",
        "ok": "23",
        "ko": "17894"
    },
    "maxResponseTime": {
        "total": "29847",
        "ok": "29847",
        "ko": "18855"
    },
    "meanResponseTime": {
        "total": "17509",
        "ok": "15625",
        "ko": "18480"
    },
    "standardDeviation": {
        "total": "5061",
        "ok": "8359",
        "ko": "210"
    },
    "percentiles1": {
        "total": "18446",
        "ok": "15564",
        "ko": "18474"
    },
    "percentiles2": {
        "total": "18695",
        "ok": "22968",
        "ko": "18662"
    },
    "percentiles3": {
        "total": "25749",
        "ok": "28634",
        "ko": "18770"
    },
    "percentiles4": {
        "total": "29152",
        "ok": "29603",
        "ko": "18838"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 16,
    "percentage": 0.6182380216383307
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
    "count": 864,
    "percentage": 33.38485316846986
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 1708,
    "percentage": 65.9969088098918
},
    "meanNumberOfRequestsPerSecond": {
        "total": "86.27",
        "ok": "29.33",
        "ko": "56.93"
    }
},
contents: {
"req_get-all-users--1569719777": {
        type: "REQUEST",
        name: "GET All Users",
path: "GET All Users",
pathFormatted: "req_get-all-users--1569719777",
stats: {
    "name": "GET All Users",
    "numberOfRequests": {
        "total": "2572",
        "ok": "864",
        "ko": "1708"
    },
    "minResponseTime": {
        "total": "1717",
        "ok": "1717",
        "ko": "17894"
    },
    "maxResponseTime": {
        "total": "29847",
        "ok": "29847",
        "ko": "18855"
    },
    "meanResponseTime": {
        "total": "17618",
        "ok": "15912",
        "ko": "18480"
    },
    "standardDeviation": {
        "total": "4886",
        "ok": "8161",
        "ko": "210"
    },
    "percentiles1": {
        "total": "18448",
        "ok": "15778",
        "ko": "18474"
    },
    "percentiles2": {
        "total": "18696",
        "ok": "23060",
        "ko": "18662"
    },
    "percentiles3": {
        "total": "25766",
        "ok": "28634",
        "ko": "18769"
    },
    "percentiles4": {
        "total": "29139",
        "ok": "29603",
        "ko": "18838"
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
    "count": 864,
    "percentage": 33.592534992223946
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 1708,
    "percentage": 66.40746500777604
},
    "meanNumberOfRequestsPerSecond": {
        "total": "85.73",
        "ok": "28.8",
        "ko": "56.93"
    }
}
    },"req_create-user-1115198575": {
        type: "REQUEST",
        name: "Create User",
path: "Create User",
pathFormatted: "req_create-user-1115198575",
stats: {
    "name": "Create User",
    "numberOfRequests": {
        "total": "16",
        "ok": "16",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "23",
        "ok": "23",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "236",
        "ok": "236",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "88",
        "ok": "88",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "54",
        "ok": "54",
        "ko": "-"
    },
    "percentiles1": {
        "total": "69",
        "ok": "69",
        "ko": "-"
    },
    "percentiles2": {
        "total": "101",
        "ok": "101",
        "ko": "-"
    },
    "percentiles3": {
        "total": "236",
        "ok": "236",
        "ko": "-"
    },
    "percentiles4": {
        "total": "236",
        "ok": "236",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 16,
    "percentage": 100.0
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
    "count": 0,
    "percentage": 0.0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "0.53",
        "ok": "0.53",
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
