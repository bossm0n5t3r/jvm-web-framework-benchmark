var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "10000",
        "ok": "8780",
        "ko": "1220"
    },
    "minResponseTime": {
        "total": "88",
        "ok": "88",
        "ko": "10000"
    },
    "maxResponseTime": {
        "total": "16181",
        "ok": "16181",
        "ko": "10186"
    },
    "meanResponseTime": {
        "total": "8117",
        "ok": "7852",
        "ko": "10019"
    },
    "standardDeviation": {
        "total": "2507",
        "ok": "2566",
        "ko": "28"
    },
    "percentiles1": {
        "total": "7767",
        "ok": "7245",
        "ko": "10006"
    },
    "percentiles2": {
        "total": "10013",
        "ok": "9400",
        "ko": "10022"
    },
    "percentiles3": {
        "total": "12133",
        "ok": "12186",
        "ko": "10078"
    },
    "percentiles4": {
        "total": "13997",
        "ok": "14100",
        "ko": "10119"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 63,
    "percentage": 0.63
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 1,
    "percentage": 0.01
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 8716,
    "percentage": 87.16000000000001
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 1220,
    "percentage": 12.2
},
    "meanNumberOfRequestsPerSecond": {
        "total": "476.19",
        "ok": "418.1",
        "ko": "58.1"
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
        "ok": "8780",
        "ko": "1220"
    },
    "minResponseTime": {
        "total": "88",
        "ok": "88",
        "ko": "10000"
    },
    "maxResponseTime": {
        "total": "16181",
        "ok": "16181",
        "ko": "10186"
    },
    "meanResponseTime": {
        "total": "8117",
        "ok": "7852",
        "ko": "10019"
    },
    "standardDeviation": {
        "total": "2507",
        "ok": "2566",
        "ko": "28"
    },
    "percentiles1": {
        "total": "7764",
        "ok": "7245",
        "ko": "10006"
    },
    "percentiles2": {
        "total": "10013",
        "ok": "9400",
        "ko": "10022"
    },
    "percentiles3": {
        "total": "12159",
        "ok": "12186",
        "ko": "10078"
    },
    "percentiles4": {
        "total": "13779",
        "ok": "14100",
        "ko": "10119"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 63,
    "percentage": 0.63
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 1,
    "percentage": 0.01
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 8716,
    "percentage": 87.16000000000001
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 1220,
    "percentage": 12.2
},
    "meanNumberOfRequestsPerSecond": {
        "total": "476.19",
        "ok": "418.1",
        "ko": "58.1"
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
