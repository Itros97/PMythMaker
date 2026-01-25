package org.softwareanvil.domain.generator

import org.softwareanvil.domain.models.Country
import org.softwareanvil.domain.models.enums.SyllableCategory
import kotlin.random.Random

class CountryGenerator(
    private val nameGenerator: NameGenerator,
    private val random: Random
) {

    fun generate(): Country {
        val foundationYear = random.nextInt(0, 1200)
        val name = nameGenerator.generate(SyllableCategory.COUNTRY)

        return Country(
            id = 0L,
            name = name,
            description = "Un país fundado en el año $foundationYear.",
            foundationYear = foundationYear,
            motto = "¡Viva $name!"

        )
    }
}

