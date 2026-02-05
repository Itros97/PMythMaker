package org.softwareanvil.domain.generator.name

import org.softwareanvil.data.repositories.syllable.SyllableRepository
import org.softwareanvil.domain.models.Syllable
import org.softwareanvil.domain.models.enums.SyllableCategory
import org.softwareanvil.domain.models.enums.SyllableType
import kotlin.random.Random

class NameGenerationService(
    private val syllableRepository: SyllableRepository
) {

    fun generateName(
        category: SyllableCategory,
        seed: Long
    ): String {
        val syllables = getSyllablesForCategory(category)
        val random = Random(seed)

        return NameGenerator(syllables, random).generate(category)
    }

    private fun getSyllablesForCategory(category: SyllableCategory): List<Syllable> =
        SyllableType.entries.flatMap { type ->
            syllableRepository.getByTypeAndCategory(type, category)
        }
}