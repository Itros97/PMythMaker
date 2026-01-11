package org.softwareanvil.domain.generator.utils

import kotlin.random.Random

fun <T> weightedRandom(
    items: List<T>,
    weight: (T) -> Int,
    random: Random
): T {
    require(items.isNotEmpty()) { "Cannot pick from empty list" }

    val totalWeight = items.sumOf(weight)
    var r = random.nextInt(totalWeight)

    for (item in items) {
        r -= weight(item)
        if (r < 0) return item
    }

    error("Weighted random selection failed")
}
