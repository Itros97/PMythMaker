package org.softwareanvil.world

import org.softwareanvil.data.repositories.country.CountryRepository
import org.softwareanvil.domain.generator.WorldGeneratorService
import org.softwareanvil.domain.models.Country

class GenerateWorldUseCase(
    private val worldGenerator: WorldGeneratorService,
    private val countryRepository: CountryRepository
) {

    fun execute(seed: Long): List<Country> {
        val countries = worldGenerator.generateCountries(seed)
        countryRepository.deleteAll()
        countries.forEach(countryRepository::insert)
        return countries
    }

    fun generateOneCountry(seed: Long): Country {
        val country = worldGenerator.generateCountry(seed)
        countryRepository.insert(country)
        return country
    }

    fun deleteOneCountry(country: Country) {
        countryRepository.deleteById(country.id)
    }

    fun getAllCountries(): List<Country> {
        return countryRepository.getAll()
    }

    fun saveSelectedCountries(countries: List<Country>) {
        countryRepository.deleteAll()
        countries.forEach(countryRepository::insert)
    }

    fun editCountry(country: Country) {
        countryRepository.updateById(country.id, country)
    }

    fun deleteAll() {
        countryRepository.deleteAll()
    }
}
