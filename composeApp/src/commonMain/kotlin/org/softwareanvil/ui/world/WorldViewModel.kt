package org.softwareanvil.ui.world

import org.softwareanvil.world.GenerateWorldUseCase
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.softwareanvil.domain.models.Country
import kotlin.random.Random

@Suppress("unused")
class WorldViewModel(
    private val generateWorldUseCase: GenerateWorldUseCase
) : ViewModel() {

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    fun generateWorld(seed: Long) {
        _countries.value = generateWorldUseCase.execute(seed)
    }

    fun load() {
        _countries.value = generateWorldUseCase.getAllCountries()
    }

    fun generateOne() {
        generateWorldUseCase.generateOneCountry(Random.nextLong())
        load()
    }

    fun delete(country: Country) {
        generateWorldUseCase.deleteOneCountry(country)
        load()
    }

    fun saveAll() {
        generateWorldUseCase.saveSelectedCountries(_countries.value)
    }

    fun editCountry(country: Country) {
        generateWorldUseCase.editCountry(country)
        load()
    }

    private val _selectedCountry = MutableStateFlow<Country?>(null)
    val selectedCountry: StateFlow<Country?> = _selectedCountry

    fun selectCountry(country: Country) {
        _selectedCountry.value = country
    }

    fun clearSelection() {
        _selectedCountry.value = null
    }

}
