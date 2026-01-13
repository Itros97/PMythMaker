package org.softwareanvil.data.repositories.syllable

import org.softwareanvil.db.Syllables
import org.softwareanvil.domain.models.Syllable
import org.softwareanvil.domain.models.enums.SyllableCategory
import org.softwareanvil.domain.models.enums.SyllableType

/**
 * Maps SQLDelight Syllables row to domain Syllable
 */
fun Syllables.toDomain(): Syllable =
    Syllable(
        id = id,
        type = SyllableType.fromDb(type),
        category = SyllableCategory.fromDb(category),
        value = syllable,
        weight = weight
    )
