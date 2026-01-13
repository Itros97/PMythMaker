package org.softwareanvil.data.repositories.country

import org.softwareanvil.db.PocketMythDatabase
import org.softwareanvil.domain.models.Country

class CountryRepositoryImpl(
    private val database: PocketMythDatabase
) : CountryRepository {

    override fun insert(country: Country) {
        database.countriesQueries.insert(
            name = country.name,
            description = country.description,
            foundationYear = country.foundationYear.toLong()
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
                    description = it.description,
                    foundationYear = 0 // TODO temoporary fix, add foundationYear to the database and rebuild the schema
                )
            }

    override fun getById(id: Long): Country? =
        database.countriesQueries
            .selectById(id)
            .executeAsOneOrNull()
            ?.let {
                Country(it.id,
                        it.name,
                        it.description,
                    foundationYear = 0) // TODO add foundationYear to the database schema
            }
}

