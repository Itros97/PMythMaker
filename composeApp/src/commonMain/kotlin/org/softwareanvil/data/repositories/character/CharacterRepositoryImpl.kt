package org.softwareanvil.data.repositories.character

import org.softwareanvil.db.PocketMythDatabase
import org.softwareanvil.domain.models.Character
import org.softwareanvil.domain.models.Country

class CharacterRepositoryImpl(
    private val database: PocketMythDatabase
) : CharacterRepository {

    override fun insert(
        firstName: String,
        lastName: String,
        age: Int?,
        countryId: Long?,
        occupation: String?,
        description: String?
    ) {
        database.charactersQueries.insert(
            first_name = firstName,
            last_name = lastName,
            age = age?.toLong(),
            country_id = countryId,
            occupation = occupation,
            description = description,
            created_at = 0L // TODO use Clock.System.now().toEpochMilliseconds(
        )
    }

    override fun getAll(): List<Character> {
        val countries = database.countriesQueries
            .selectAll()
            .executeAsList()
            .associateBy { it.id }

        return database.charactersQueries
            .selectAll()
            .executeAsList()
            .map { row ->
                Character(
                    id = row.id,
                    firstName = row.first_name,
                    lastName = row.last_name,
                    age = row.age?.toInt(),
                    country = row.country_id?.let { cid ->
                        countries[cid]?.let {
                            Country(it.id, it.name, it.description, foundationYear = 0)
                        }
                    },
                    occupation = row.occupation,
                    description = row.description,
                    createdAt = row.created_at
                )
            }
    }

    override fun getById(id: Long): Character? =
        database.charactersQueries
            .selectById(id)
            .executeAsOneOrNull()
            ?.let {
                Character(
                    id = it.id,
                    firstName = it.first_name,
                    lastName = it.last_name,
                    age = it.age?.toInt(),
                    country = null,
                    occupation = it.occupation,
                    description = it.description,
                    createdAt = it.created_at
                )
            }

    override fun deleteById(id: Long) {
        database.charactersQueries.deleteById(id)
    }


}
