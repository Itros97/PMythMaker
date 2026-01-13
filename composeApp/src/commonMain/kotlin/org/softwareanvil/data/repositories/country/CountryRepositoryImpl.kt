package org.softwareanvil.data.repositories.country

import org.softwareanvil.db.CountriesQueries
import org.softwareanvil.db.PocketMythDatabase
import org.softwareanvil.domain.models.Country

class CountryRepositoryImpl(
    private val queries: CountriesQueries
) : CountryRepository {

    override fun insert(country: Country) {
        queries.insert(
            name = country.name,
            description = country.description,
            foundationYear = country.foundationYear?.toLong()
        )
    }

    override fun getAll(): List<Country> =
        queries.selectAll().executeAsList().map {
            Country(
                id = it.id,
                name = it.name,
                description = it.description,
                foundationYear = it.foundationYear?.toInt()
            )
        }

    override fun getById(id: Long): Country? =
        queries.selectById(id).executeAsOneOrNull()?.let {
            Country(
                id = it.id,
                name = it.name,
                description = it.description,
                foundationYear = it.foundationYear?.toInt()
            )
        }

    override fun deleteAll() {
        queries.deleteAll()
    }

}


