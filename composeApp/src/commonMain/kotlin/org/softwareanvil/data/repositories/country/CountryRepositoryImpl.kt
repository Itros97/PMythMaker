package org.softwareanvil.data.repositories.country

import org.softwareanvil.db.CountriesQueries
import org.softwareanvil.domain.models.Country

class CountryRepositoryImpl(
    private val queries: CountriesQueries
) : CountryRepository {

    override fun getAll(): List<Country> =
        queries.selectAll().executeAsList().map { it.toDomain() }

    override fun getById(id: Long): Country? =
        queries.selectById(id).executeAsOneOrNull()?.toDomain()

    override fun getRandomCountry(): Country? =
        queries.selectRandom().executeAsOneOrNull()?.toDomain()

    override fun count(): Long =
        queries.count().executeAsOne()

    override fun insert(country: Country): Long {
        queries.insert(
            name = country.name,
            description = country.description,
            foundation_year = country.foundationYear?.toLong(),
            motto = country.motto,
            created_at = country.createdAt
        )
        return queries.selectAll()
            .executeAsList()
            .last()
            .id
    }

    override fun updateById(id: Long, country: Country) {
        queries.updateById(
            id = id,
            name = country.name,
            description = country.description,
            foundation_year = country.foundationYear?.toLong(),
            motto = country.motto
        )
    }

    override fun deleteById(id: Long) {
        queries.deleteById(id)
    }

    override fun deleteAll() {
        queries.deleteAll()
    }

    private fun org.softwareanvil.db.Countries.toDomain(): Country =
        Country(
            id = id,
            name = name,
            description = description ?: "",
            foundationYear = foundation_year?.toInt(),
            motto = motto ?: "",
            createdAt = created_at
        )
}