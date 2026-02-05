package org.softwareanvil.domain.generator.character

import org.softwareanvil.domain.generator.GenerationConfig
import org.softwareanvil.domain.models.Character
import org.softwareanvil.domain.models.Country
import kotlin.random.Random

class CharacterGenerationService(
    private val nameGenerator: CharacterNameGenerator,
    private val config: GenerationConfig = GenerationConfig()
) {

    fun generateCharacter(
        seed: Long,
        country: Country?
    ): Character {
        val random = Random(seed)
        val (firstName, lastName) = nameGenerator.generateFullName(seed)

        return CharacterGenerator(
            firstName = firstName,
            lastName = lastName,
            country = country,
            random = random,
            config = config
        ).generate()
    }
}