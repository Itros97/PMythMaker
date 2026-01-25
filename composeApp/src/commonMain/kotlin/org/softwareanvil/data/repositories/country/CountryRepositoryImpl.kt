package org.softwareanvil.data.repositories.country

import org.softwareanvil.db.CountriesQueries
import org.softwareanvil.domain.models.Country

class CountryRepositoryImpl(
    private val queries: CountriesQueries
) : CountryRepository {

    override fun getAll(): List<Country> =
        queries.selectAll().executeAsList().map {
            Country(
                id = it.id,
                name = it.name,
                description = it.description,
                foundationYear = it.foundationYear?.toInt(),
                motto = it.motto
            )
        }

    override fun getById(id: Long): Country? =
        queries.selectById(id).executeAsOneOrNull()?.let {
            Country(
                id = it.id,
                name = it.name,
                description = it.description,
                foundationYear = it.foundationYear?.toInt(),
                motto = it.motto
            )
        }

    override fun insert(country: Country) {
        queries.insert(
            name = country.name,
            description = country.description,
            foundationYear = country.foundationYear?.toLong(),
            motto = country.motto
        )
    }

    override fun updateById(id: Long, country: Country) {
        queries.updateById(
            id = id,
            name = country.name,
            description = country.description,
            foundationYear = country.foundationYear?.toLong(),
            motto = country.motto
        )
    }

    override fun deleteById(id: Long) {
        queries.deleteById(id)
    }

    override fun deleteAll() {
        queries.deleteAll()
    }


}


