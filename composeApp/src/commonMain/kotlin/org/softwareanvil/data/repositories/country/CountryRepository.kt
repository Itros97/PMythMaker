package org.softwareanvil.data.repositories.country

import org.softwareanvil.domain.models.Country

interface CountryRepository {
    fun insert(country: Country)
    fun getAll(): List<Country>
    fun getById(id: Long): Country?
    fun deleteAll()
    fun updateById(id: Long, country: Country)
}
