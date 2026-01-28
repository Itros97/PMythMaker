package org.softwareanvil.data.repositories.character

import org.softwareanvil.db.CharactersQueries
import org.softwareanvil.domain.models.Character

class CharacterRepositoryImpl(
    private val queries: CharactersQueries
) : CharacterRepository {

    override fun selectAll(): List<Character> =
        queries.selectAll()
            .executeAsList()
            .map { it.toDomain() }

    override fun selectById(id: Long): Character? =
        queries.selectById(id)
            .executeAsOneOrNull()
            ?.toDomain()

    override fun selectByCountryName(countryName: String): List<Character> =
        queries.selectByCountryName(countryName)
            .executeAsList()
            .map { it.toDomain() }

    override fun insert(character: Character): Long {
        queries.insert(
            first_name = character.firstName,
            last_name = character.lastName,
            age = character.age?.toLong(),
            country_id = character.country?.id,
            occupation = character.occupation,
            description = character.description,
            created_at = character.createdAt
        )
        return character.id
    }

    override fun updateById(id: Long, character: Character) {
        queries.updateById(
            id = id,
            first_name = character.firstName,
            last_name = character.lastName,
            age = character.age?.toLong(),
            country_id = character.country?.id,
            occupation = character.occupation,
            description = character.description,
            created_at = character.createdAt
        )
    }

    override fun deleteById(id: Long) {
        queries.deleteById(id)
    }
}
