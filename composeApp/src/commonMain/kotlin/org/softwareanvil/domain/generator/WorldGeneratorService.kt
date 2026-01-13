package org.softwareanvil.domain.generator

import org.softwareanvil.data.repositories.syllable.SyllableRepository
import org.softwareanvil.domain.models.Country
import org.softwareanvil.domain.models.enums.SyllableCategory
import org.softwareanvil.domain.models.enums.SyllableType
import kotlin.random.Random

class WorldGeneratorService(
    private val syllableRepository: SyllableRepository
) {

    fun generateCountries(seed: Long): List<Country> {
        val worldRandom = Random(seed)

        val countryCount = worldRandom.nextInt(3, 9) // densidad del mundo

        val syllables =
            syllableRepository.getByTypeAndCategory(SyllableType.PREFIX, SyllableCategory.COUNTRY) +
                    syllableRepository.getByTypeAndCategory(SyllableType.CORE, SyllableCategory.COUNTRY) +
                    syllableRepository.getByTypeAndCategory(SyllableType.SUFFIX, SyllableCategory.COUNTRY)

        val nameGenerator = NameGenerator(syllables, worldRandom)

        return (0 until countryCount).map {
            CountryGenerator(nameGenerator, worldRandom).generate()
        }
    }
}
