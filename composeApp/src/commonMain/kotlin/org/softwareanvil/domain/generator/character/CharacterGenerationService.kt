package org.softwareanvil.domain.generator.character

import org.softwareanvil.data.repositories.country.CountryRepository
import org.softwareanvil.domain.generator.GenerationConfig
import org.softwareanvil.domain.models.Character
import org.softwareanvil.domain.models.Country
import kotlin.random.Random

class CharacterGenerationService(
    private val nameGenerator: CharacterNameGenerator,
    private val countryRepository: CountryRepository,
    private val config: GenerationConfig = GenerationConfig()
) {

    // Genera character con país aleatorio de BD
    fun generateCharacter(seed: Long): Character {
        val random = Random(seed)
        val (firstName, lastName) = nameGenerator.generateFullName(seed)

        val country = countryRepository.getRandomCountry()

        return CharacterGenerator(
            firstName = firstName,
            lastName = lastName,
            country = country,
            random = random,
            config = config
        ).generate()
    }

    fun createCharacter(
        firstName: String,
        lastName: String,
        country: Country?,
        seed: Long
    ): Character {
        return CharacterGenerator(
            firstName = firstName,
            lastName = lastName,
            country = country,
            random = Random(seed),
            config = config
        ).generate()
    }

    fun generateCharacterWithoutCountry(seed: Long): Character {
        val random = Random(seed)
        val (firstName, lastName) = nameGenerator.generateFullName(seed)

        return CharacterGenerator(
            firstName = firstName,
            lastName = lastName,
            country = null,
            random = random,
            config = config
        ).generate()
    }
}