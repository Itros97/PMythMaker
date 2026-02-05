package org.softwareanvil.domain.generator.country

import org.softwareanvil.domain.generator.GenerationConfig
import org.softwareanvil.domain.generator.Generator
import org.softwareanvil.domain.models.Country
import kotlin.random.Random

class CountryGenerator(
    private val countryName: String,
    private val random: Random,
    private val config: GenerationConfig = GenerationConfig()
) : Generator<Country> {

    override fun generate(): Country = Country(
        name = countryName,
        description = config.countryDescriptionTemplate(countryName),
        foundationYear = random.nextInt(config.minFoundationYear, config.maxFoundationYear),
        motto = config.countryMottoTemplate(countryName)
    )
}
