package org.softwareanvil.domain.models

data class Character(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val age: Int?,
    val country: Country?,
    val occupation: String?,
    val description: String?,
    val createdAt: Long
)
