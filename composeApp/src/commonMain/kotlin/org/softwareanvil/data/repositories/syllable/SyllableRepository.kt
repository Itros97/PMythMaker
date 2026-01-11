package org.softwareanvil.data.repositories.syllable

import org.softwareanvil.domain.models.Syllable
import org.softwareanvil.domain.models.enums.SyllableCategory
import org.softwareanvil.domain.models.enums.SyllableType

interface SyllableRepository {
    fun getByTypeAndCategory(
        type: SyllableType,
        category: SyllableCategory
    ): List<Syllable>

    fun isEmpty(): Boolean
}

