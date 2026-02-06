package org.softwareanvil.domain.generator.world

import org.softwareanvil.domain.generator.character.CharacterGenerationService
import org.softwareanvil.domain.generator.country.CountryGenerator
import org.softwareanvil.domain.generator.name.NameGenerationService
import org.softwareanvil.domain.models.Character
import org.softwareanvil.domain.models.Country
import org.softwareanvil.domain.models.enums.SyllableCategory
import kotlin.random.Random

class WorldGeneratorService(
    private val nameGenerationService: NameGenerationService,
    private val characterGenerationService: CharacterGenerationService
) {

    fun generateCountry(seed: Long): Country {
        val random = Random(seed)

        val name = nameGenerationService.generateName(
            category = SyllableCategory.COUNTRY,
            seed = random.nextLong()
        )

        return CountryGenerator(
            countryName = name,
            random = random
        ).generate()
    }

    fun generateCharacter(seed: Long): Character {
        return characterGenerationService.generateCharacter(seed)
    }

    fun generateCharacterWithCountry(seed: Long, country: Country?): Character {
        val random = Random(seed)

        val firstName = nameGenerationService.generateName(
            category = SyllableCategory.PERSON,
            seed = random.nextLong()
        )

        val lastName = nameGenerationService.generateName(
            category = SyllableCategory.PERSON,
            seed = random.nextLong()
        )

        return characterGenerationService.createCharacter(
            firstName = firstName,
            lastName = lastName,
            country = country,
            seed = seed
        )
    }
}