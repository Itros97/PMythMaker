package org.softwareanvil.data.repositories.country

import org.softwareanvil.domain.models.Country

interface CountryRepository {
    fun getAll(): List<Country>
    fun getById(id: Long): Country?
    fun getRandomCountry(): Country?
    fun count(): Long
    fun insert(country: Country): Long
    fun updateById(id: Long, country: Country)
    fun deleteById(id: Long)
    fun deleteAll()

}
