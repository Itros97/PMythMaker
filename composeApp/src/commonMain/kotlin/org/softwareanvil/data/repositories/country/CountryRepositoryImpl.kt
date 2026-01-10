package org.softwareanvil.data.repositories.country

import org.softwareanvil.db.PocketMythDatabase
import org.softwareanvil.domain.models.Country

class CountryRepositoryImpl(
    private val database: PocketMythDatabase
) : CountryRepository {

    override fun insert(name: String, description: String?) {
        database.countriesQueries.insert(
            name = name,
            description = description,
            created_at = 0L // TODO use Clock.System.now().toEpochMilliseconds()
        )
    }

    override fun getAll(): List<Country> =
        database.countriesQueries
            .selectAll()
            .executeAsList()
            .map {
                Country(
                    id = it.id,
                    name = it.name,
                    description = it.description
                )
            }

    override fun getById(id: Long): Country? =
        database.countriesQueries
            .selectById(id)
            .executeAsOneOrNull()
            ?.let {
                Country(it.id, it.name, it.description)
            }
}

