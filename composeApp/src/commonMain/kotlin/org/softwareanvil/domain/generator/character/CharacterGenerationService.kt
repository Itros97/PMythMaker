package org.softwareanvil.domain.generator.character

import org.softwareanvil.domain.generator.name.NameGenerationService
import org.softwareanvil.domain.models.Character
import org.softwareanvil.domain.models.Country
import org.softwareanvil.domain.models.enums.SyllableCategory
import kotlin.random.Random

class CharacterGenerationService(
    private val nameService: NameGenerationService
) {

    fun generateCharacter(
        seed: Long,
        country: Country?
    ): Character {
        val random = Random(seed)

        val firstName = nameService.generateName(
            category = SyllableCategory.PERSON,
            seed = random.nextLong()
        )

        val lastName = nameService.generateName(
            category = SyllableCategory.PERSON,
            seed = random.nextLong()
        )

        return CharacterGenerator(
            firstName = firstName,
            lastName = lastName,
            country = country,
            random = random
        ).generate()
    }
    
}
