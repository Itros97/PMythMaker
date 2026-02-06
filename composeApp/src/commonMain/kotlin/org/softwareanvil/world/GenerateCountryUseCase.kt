package org.softwareanvil.world

import org.softwareanvil.data.repositories.character.CharacterRepository
import org.softwareanvil.data.repositories.country.CountryRepository
import org.softwareanvil.domain.generator.world.WorldGeneratorService
import org.softwareanvil.domain.models.Character
import org.softwareanvil.domain.models.Country

class GenerateWorldUseCase(
    private val worldGenerator: WorldGeneratorService,
    private val countryRepository: CountryRepository,
    private val characterRepository: CharacterRepository
) {

    fun generateOneCountry(seed: Long): Country {
        return worldGenerator.generateCountry(seed)
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

    /* ----------------------------
     * CHARACTERS
     * ---------------------------- */

    fun generateCharacter(
        seed: Long,
        country: Country?
    ): Character {
        return worldGenerator.generateCharacterWithCountry(seed, country)
    }

    fun saveCharacter(character: Character) {
        characterRepository.insert(character)
    }

    fun getAllCharacters(): List<Character> =
        characterRepository.selectAll()

    fun deleteCharacter(character: Character) {
        characterRepository.deleteById(character.id)
    }

}
