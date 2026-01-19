package org.softwareanvil.domain.models

data class Country(
    val id: Long = 0,
    val name: String,
    val description: String?,
    val foundationYear: Int?
)

