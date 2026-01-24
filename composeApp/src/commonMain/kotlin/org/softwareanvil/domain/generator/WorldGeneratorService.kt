package org.softwareanvil.domain.generator

import org.softwareanvil.data.repositories.syllable.SyllableRepository
import org.softwareanvil.domain.models.Country
import org.softwareanvil.domain.models.enums.SyllableCategory
import org.softwareanvil.domain.models.enums.SyllableType
import kotlin.random.Random

class WorldGeneratorService(
    private val syllableRepository: SyllableRepository
) {

    private fun buildNameGenerator(random: Random): NameGenerator {
        val syllables =
            syllableRepository.getByTypeAndCategory(SyllableType.PREFIX, SyllableCategory.COUNTRY) +
                    syllableRepository.getByTypeAndCategory(SyllableType.CORE, SyllableCategory.COUNTRY) +
                    syllableRepository.getByTypeAndCategory(SyllableType.SUFFIX, SyllableCategory.COUNTRY)

        return NameGenerator(
            syllables = syllables,
            random = random
        )
    }

    fun generateCountry(seed: Long): Country {
        val random = Random(seed)
        val nameGenerator = buildNameGenerator(random)

        return CountryGenerator(
            nameGenerator = nameGenerator,
            random = random
        ).generate()
    }

    fun generateCountries(seed: Long): List<Country> {
        val random = Random(seed)
        val nameGenerator = buildNameGenerator(random)
        val count = random.nextInt(3, 9)

        val generator = CountryGenerator(
            nameGenerator = nameGenerator,
            random = random
        )

        return List(count) { generator.generate() }
    }
}
