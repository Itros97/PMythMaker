package org.softwareanvil.domain.generator.character

import org.softwareanvil.domain.generator.GenerationConfig
import org.softwareanvil.domain.generator.Generator
import org.softwareanvil.domain.models.Character
import org.softwareanvil.domain.models.Country
import kotlin.random.Random

class CharacterGenerator(
    private val firstName: String,
    private val lastName: String,
    private val country: Country?,
    private val random: Random,
    private val config: GenerationConfig = GenerationConfig()
) : Generator<Character> {

    override fun generate(): Character = Character(
        id = 0,
        firstName = firstName,
        lastName = lastName,
        age = random.nextInt(config.minAge, config.maxAge),
        country = country,
        occupation = null,
        description = null,
        createdAt = 0L
    )
}