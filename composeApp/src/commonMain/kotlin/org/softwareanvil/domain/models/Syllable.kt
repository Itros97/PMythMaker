package org.softwareanvil.domain.models

import org.softwareanvil.domain.models.enums.SyllableCategory
import org.softwareanvil.domain.models.enums.SyllableType

data class Syllable(
    val id: Long,
    val type: SyllableType,
    val category: SyllableCategory,
    val value: String,
    val weight: Long
)
