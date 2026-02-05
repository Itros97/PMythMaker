package org.softwareanvil.domain.generator.utils

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun currentTimeMillis(): Long = Clock.System.now().toEpochMilliseconds()