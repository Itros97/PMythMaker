package org.softwareanvil.data.repositories.character

import org.softwareanvil.domain.models.Character

interface CharacterRepository {

    fun selectAll(): List<Character>
    fun selectById(id: Long): Character?
    fun selectByCountryName(countryName: String): List<Character>
    fun insert(character: Character): Long
    fun updateById(id: Long, character: Character)
    fun deleteById(id: Long)


}
