package org.softwareanvil.data.repositories.country

import org.softwareanvil.domain.models.Country

interface CountryRepository {
    fun insert(name: String, description: String?)
    fun getAll(): List<Country>
    fun getById(id: Long): Country?
}
