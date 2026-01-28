package org.softwareanvil.domain.generator.name

import org.softwareanvil.data.repositories.syllable.SyllableRepository
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
        val random = Random(seed)

        val syllables =
            syllableRepository.getByTypeAndCategory(SyllableType.PREFIX, category) +
                    syllableRepository.getByTypeAndCategory(SyllableType.CORE, category) +
                    syllableRepository.getByTypeAndCategory(SyllableType.SUFFIX, category)

        return NameGenerator(
            syllables = syllables,
            random = random
        ).generate(category)
    }
}
