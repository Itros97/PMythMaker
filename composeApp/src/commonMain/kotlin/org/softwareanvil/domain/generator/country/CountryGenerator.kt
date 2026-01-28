package org.softwareanvil.domain.generator.country

import org.softwareanvil.domain.models.Country
import kotlin.random.Random

class CountryGenerator(
    private val countryName: String,
    private val random: Random
) {

    fun generate(): Country =
        Country(
            name = countryName,
            description = "La maravillosa nación de $countryName.",
            foundationYear = random.nextInt(0, 2000),
            motto = "¡$countryName al poder!"
        )
}
