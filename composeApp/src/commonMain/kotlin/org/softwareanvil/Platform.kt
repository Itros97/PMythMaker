package org.softwareanvil

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform