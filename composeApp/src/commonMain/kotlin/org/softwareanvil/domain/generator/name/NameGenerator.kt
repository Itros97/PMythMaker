package org.softwareanvil.domain.generator.name

import org.softwareanvil.domain.generator.utils.weightedRandom
import org.softwareanvil.domain.models.Syllable
import org.softwareanvil.domain.models.enums.SyllableCategory
import org.softwareanvil.domain.models.enums.SyllableType
import kotlin.random.Random

class NameGenerator(
    private val syllables: List<Syllable>,
    private val random: Random,
    private val prefixProbability: Float = 0.9f,
    private val suffixProbability: Float = 0.7f
) {

    fun generate(category: SyllableCategory): String {
        val byType = syllables
            .filter { it.category == category }
            .groupBy { it.type }

        val coreList = byType[SyllableType.CORE]
        require(!coreList.isNullOrEmpty()) {
            "At least one CORE syllable is required for $category"
        }

        val prefix = byType[SyllableType.PREFIX]
            ?.takeIf { random.nextFloat() < prefixProbability }
            ?.let { pickSyllable(it) }

        val core = pickSyllable(coreList)

        val suffix = byType[SyllableType.SUFFIX]
            ?.takeIf { random.nextFloat() < suffixProbability }
            ?.let { pickSyllable(it) }

        return buildName(prefix, core, suffix)
    }

    private fun pickSyllable(list: List<Syllable>): Syllable =
        weightedRandom(list, { it.weight.toInt() }, random)

    private fun buildName(prefix: Syllable?, core: Syllable, suffix: Syllable?): String =
        buildString {
            prefix?.let { append(it.value) }
            append(core.value)
            suffix?.let { append(it.value) }
        }.replaceFirstChar { it.uppercase() }
}