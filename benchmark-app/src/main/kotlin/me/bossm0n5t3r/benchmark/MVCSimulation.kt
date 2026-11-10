package me.bossm0n5t3r.benchmark

import me.bossm0n5t3r.benchmark.Constants.MVC_URL

class MVCSimulation :
    AppSimulation(
        name = "MVC",
        baseUrl = MVC_URL,
        path = "/mvc/external/no-db",
    )
