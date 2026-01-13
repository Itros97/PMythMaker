package org.softwareanvil.data.repositories.character

import org.softwareanvil.domain.models.Character

interface CharacterRepository {

    fun insert(
        firstName: String,
        lastName: String,
        age: Int?,
        countryId: Long?,
        occupation: String?,
        description: String?,
    )

    fun getAll(): List<Character>
    fun getById(id: Long): Character?
    fun deleteById(id: Long)
}
