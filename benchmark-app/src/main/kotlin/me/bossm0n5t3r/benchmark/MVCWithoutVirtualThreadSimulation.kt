package me.bossm0n5t3r.benchmark

import me.bossm0n5t3r.benchmark.Constants.MVC_WITHOUT_VIRTUAL_THREAD_URL

class MVCWithoutVirtualThreadSimulation :
    AppSimulation(
        name = "MVC without Virtual Thread",
        baseUrl = MVC_WITHOUT_VIRTUAL_THREAD_URL,
        path = "/mvc-without-virtual-thread/external/no-db",
    )
