package org.softwareanvil.ui.world

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.softwareanvil.domain.models.Country
import org.softwareanvil.world.GenerateWorldUseCase
import kotlin.random.Random

class WorldViewModel(
    private val generateWorldUseCase: GenerateWorldUseCase
) : ViewModel() {

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    private val _generatedCountry = MutableStateFlow<Country?>(null)
    val generatedCountry: StateFlow<Country?> = _generatedCountry

    private val _selectedCountry = MutableStateFlow<Country?>(null)
    val selectedCountry: StateFlow<Country?> = _selectedCountry

    init {
        load()
    }

    /* ───────────────────────────────
     * LOAD DATA AND USED TO REFRESH
     * ─────────────────────────────── */
    fun load() {
        _countries.value = generateWorldUseCase.getAllCountries()
    }


    fun generateOne() {
        _generatedCountry.value =
            generateWorldUseCase.generateOneCountry(Random.nextLong())
    }

    fun discardGenerated() {
        _generatedCountry.value = null
    }

    fun saveGenerated() {
        val country = _generatedCountry.value ?: return
        generateWorldUseCase.saveCountry(country)
        _generatedCountry.value = null
        load()
    }

    fun editCountry(country: Country) {
        generateWorldUseCase.updateCountry(country)
        load()
    }

    fun delete(country: Country) {
        generateWorldUseCase.deleteCountry(country)
        load()
    }

    fun deleteAll() {
        generateWorldUseCase.deleteAllCountries()
        load()
    }

    fun selectCountry(country: Country) {
        _selectedCountry.value = country
    }

    fun clearSelection() {
        _selectedCountry.value = null
    }
}
