package org.softwareanvil.world

import org.softwareanvil.data.repositories.country.CountryRepository
import org.softwareanvil.domain.generator.world.WorldGeneratorService
import org.softwareanvil.domain.models.Country

class GenerateWorldUseCase(
    private val worldGenerator: WorldGeneratorService,
    private val countryRepository: CountryRepository
) {

    fun generateOneCountry(seed: Long): Country {
        return worldGenerator.generateCountry(seed)
    }

    fun generateCountries(seed: Long): List<Country> {
        return worldGenerator.generateCountries(seed)
    }

    fun saveCountry(country: Country) {
        countryRepository.insert(country)
    }

    fun updateCountry(country: Country) {
        countryRepository.updateById(country.id, country)
    }

    fun deleteCountry(country: Country) {
        countryRepository.deleteById(country.id)
    }

    fun deleteAllCountries() {
        countryRepository.deleteAll()
    }

    fun getAllCountries(): List<Country> {
        return countryRepository.getAll()
    }
}
