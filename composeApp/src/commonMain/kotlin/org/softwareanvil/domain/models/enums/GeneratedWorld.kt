package org.softwareanvil.domain.models.enums

import org.softwareanvil.domain.models.Character
import org.softwareanvil.domain.models.Country

data class GeneratedWorld(
    val countries: List<Country>,
    val character: List<Character>
)
