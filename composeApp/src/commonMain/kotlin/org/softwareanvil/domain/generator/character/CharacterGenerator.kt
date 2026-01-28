package org.softwareanvil.domain.generator.character

import org.softwareanvil.domain.models.Character
import org.softwareanvil.domain.models.Country
import kotlin.random.Random

class CharacterGenerator(
    private val firstName: String,
    private val lastName: String,
    private val country: Country?,
    private val random: Random
) {

    fun generate(): Character {
        return Character(
            id = 0,
            firstName = firstName,
            lastName = lastName,
            age = random.nextInt(16, 80),
            country = country,
            occupation = null,
            description = null,
            createdAt = 0L
        )
    }
}



