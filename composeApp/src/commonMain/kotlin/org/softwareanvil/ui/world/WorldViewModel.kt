package org.softwareanvil.ui.world

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.softwareanvil.domain.models.Character
import org.softwareanvil.domain.models.Country
import org.softwareanvil.world.GenerateWorldUseCase
import kotlin.random.Random

class WorldViewModel(
    private val generateWorldUseCase: GenerateWorldUseCase
) : ViewModel() {

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    private val _generatedCountry = MutableStateFlow<Country?>(null)
    val generatedCountry: StateFlow<Country?> = _generatedCountry

    private val _selectedCountry = MutableStateFlow<Country?>(null)
    val selectedCountry: StateFlow<Country?> = _selectedCountry

    private val _generatedCharacter = MutableStateFlow<Character?>(null)
    val generatedCharacter: StateFlow<Character?> = _generatedCharacter
    private val _selectedCharacter = MutableStateFlow<Character?>(null)
    val selectedCharacter: StateFlow<Character?> = _selectedCharacter

    /* ───────────────────────────────
     * INITIALIZATION TO LOAD DATA ON START AND RELOAD
     * ─────────────────────────────── */

    init {
        loadCountries()
        loadCharacters()
    }

    /* ───────────────────────────────
     * LOAD COUNTRIES
     * ─────────────────────────────── */

    fun loadCountries() {
        _countries.value = generateWorldUseCase.getAllCountries()
    }

    /* ───────────────────────────────
     * LOAD CHARACTERS
     * ─────────────────────────────── */

    fun loadCharacters() {
        _characters.value = generateWorldUseCase.getAllCharacters()
    }

    /* ───────────────────────────────
     * LOAD ALL (COUNTRIES + CHARACTERS)
     * ─────────────────────────────── */

    fun loadAll() {
        loadCountries()
        loadCharacters()
    }

    /* ───────────────────────────────
     * COUNTRY ACTIONS
     * ─────────────────────────────── */

    fun generateCountry() {
        _generatedCountry.value =
            generateWorldUseCase.generateOneCountry(Random.nextLong())
    }

    fun saveGeneratedCountry() {
        val country = _generatedCountry.value ?: return
        generateWorldUseCase.saveCountry(country)
        _generatedCountry.value = null
        loadCountries()
    }

    fun discardGeneratedCountry() {
        _generatedCountry.value = null
    }

    fun editCountry(country: Country) {
        generateWorldUseCase.updateCountry(country)
        loadCountries()
    }

    fun deleteCountry(country: Country) {
        generateWorldUseCase.deleteCountry(country)
        loadCountries()
    }

    fun deleteAllCountries() {
        generateWorldUseCase.deleteAllCountries()
        loadCountries()
    }

    fun selectCountry(country: Country) {
        _selectedCountry.value = country
    }

    fun clearSelection() {
        _selectedCountry.value = null
    }

    /* ───────────────────────────────
     * CHARACTER
     * ─────────────────────────────── */

    fun generateCharacter() {
        _generatedCharacter.value =
            generateWorldUseCase.generateCharacter(
                seed = Random.nextLong(),
                country = null
            )
    }

    fun saveGeneratedCharacter() {
        val character = _generatedCharacter.value ?: return
        generateWorldUseCase.saveCharacter(character)
        _generatedCharacter.value = null
    }

    fun discardGeneratedCharacter() {
        _generatedCharacter.value = null
    }

    fun selectCharacter(character: Character) {
        _selectedCharacter.value = character
    }

    fun clearSelectedCharacter() {
        _selectedCharacter.value = null
    }

    fun deleteCharacter(character: Character) {
        generateWorldUseCase.deleteCharacter(character)
        // loadCharacters()
    }


}
