package org.softwareanvil.domain.generator

import org.softwareanvil.domain.generator.utils.weightedRandom
import kotlin.random.Random
import org.softwareanvil.domain.models.Syllable
import org.softwareanvil.domain.models.enums.SyllableCategory
import org.softwareanvil.domain.models.enums.SyllableType

class NameGenerator(
    private val syllables: List<Syllable>,
    private val random: Random
) {

    fun generate(category: SyllableCategory): String {
        val byType = syllables
            .filter { it.category == category }
            .groupBy { it.type }

        val coreList = byType[SyllableType.CORE]
        require(!coreList.isNullOrEmpty()) {
            "At least one CORE syllable is required for $category"
        }

        val prefix = byType[SyllableType.PREFIX]?.let { pick(it) }
        val core = pick(coreList)
        val suffix = byType[SyllableType.SUFFIX]?.let { pick(it) }

        return buildString {
            prefix?.let { append(it.value) }
            append(core.value)
            suffix?.let { append(it.value) }
        }.replaceFirstChar { it.uppercase() }
    }

    private fun pick(list: List<Syllable>): Syllable =
        weightedRandom(list, { it.weight.toInt() }, random)
}
