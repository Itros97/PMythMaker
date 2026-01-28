package org.softwareanvil.domain.generator.character

import org.softwareanvil.domain.generator.name.NameGenerationService
import org.softwareanvil.domain.models.enums.SyllableCategory
import kotlin.random.Random

class CharacterNameGenerator(
    private val nameGenerationService: NameGenerationService
) {

    fun generateFullName(seed: Long): Pair<String, String> {
        val random = Random(seed)

        val firstName = nameGenerationService.generateName(
            category = SyllableCategory.PERSON,
            seed = random.nextLong()
        )

        val lastName = nameGenerationService.generateName(
            category = SyllableCategory.PERSON,
            seed = random.nextLong()
        )

        return firstName to lastName
    }
}