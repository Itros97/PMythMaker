package org.softwareanvil

import androidx.compose.runtime.*
import org.softwareanvil.ui.Screen
import org.softwareanvil.ui.generator.CharacterGeneratorScreen
import org.softwareanvil.ui.generator.GenerateMenuScreen
import org.softwareanvil.ui.generator.GeneratorScreen
import org.softwareanvil.ui.home.HomeScreen
import org.softwareanvil.ui.library.LibraryMenuScreen
import org.softwareanvil.ui.library.character.CharacterDetailScreen
import org.softwareanvil.ui.library.character.CharacterLibraryScreen
import org.softwareanvil.ui.library.country.CountryDetailScreen
import org.softwareanvil.ui.library.country.CountryLibraryScreen
import org.softwareanvil.ui.metadata.MetadataScreen
import org.softwareanvil.ui.settings.SettingsScreen
import org.softwareanvil.ui.theme.PocketMythsmithTheme

@Composable
fun App() {
    val viewModel = remember {
        WorldFactory.createWorldViewModel()
    }

    var screen by remember { mutableStateOf(Screen.HOME) }
    var isDarkTheme by remember { mutableStateOf(false) }

    PocketMythsmithTheme(isDarkTheme = isDarkTheme) {
        when (screen) {

            Screen.HOME -> HomeScreen(
                onGenerate = { screen = Screen.GENERATE_MENU },
                onLibrary = { screen = Screen.LIBRARY_MENU },
                onSettings = { screen = Screen.SETTINGS },
                onMetadata = { screen = Screen.METADATA }
            )

            // ─────────────────────────────────────────────────────────
            // GENERADORES
            // ─────────────────────────────────────────────────────────

            Screen.GENERATE_MENU -> GenerateMenuScreen(
                onGenerateCountries = { screen = Screen.GENERATE_COUNTRIES },
                onGenerateCharacters = { screen = Screen.GENERATE_CHARACTERS },
                onBack = { screen = Screen.HOME }
            )

            Screen.GENERATE_COUNTRIES -> GeneratorScreen(
                viewModel = viewModel,
                onBack = { screen = Screen.GENERATE_MENU }
            )

            Screen.GENERATE_CHARACTERS -> CharacterGeneratorScreen(
                viewModel = viewModel,
                onBack = { screen = Screen.GENERATE_MENU }
            )

            // ─────────────────────────────────────────────────────────
            // BIBLIOTECAS
            // ─────────────────────────────────────────────────────────

            Screen.LIBRARY_MENU -> LibraryMenuScreen(
                onBack = { screen = Screen.HOME },
                onCountriesClick = { screen = Screen.COUNTRY_LIBRARY },
                onCharactersClick = { screen = Screen.CHARACTER_LIBRARY }
            )

            Screen.COUNTRY_LIBRARY -> CountryLibraryScreen(
                viewModel = viewModel,
                onBack = { screen = Screen.LIBRARY_MENU },
                onEdit = { screen = Screen.COUNTRY_DETAIL }
            )

            Screen.CHARACTER_LIBRARY -> CharacterLibraryScreen(
                viewModel = viewModel,
                onBack = { screen = Screen.LIBRARY_MENU },
                onEdit = { screen = Screen.CHARACTER_DETAIL }
            )

            // ─────────────────────────────────────────────────────────
            // DETALLES / EDICIÓN
            // ─────────────────────────────────────────────────────────

            Screen.COUNTRY_DETAIL -> CountryDetailScreen(
                viewModel = viewModel,
                onBack = { screen = Screen.COUNTRY_LIBRARY }
            )

            Screen.CHARACTER_DETAIL -> CharacterDetailScreen(
                viewModel = viewModel,
                onBack = { screen = Screen.CHARACTER_LIBRARY }
            )

            // ─────────────────────────────────────────────────────────
            // AJUSTES / INFO
            // ─────────────────────────────────────────────────────────

            Screen.SETTINGS -> SettingsScreen(
                isDarkTheme = isDarkTheme,
                onThemeChange = { isDarkTheme = it },
                onBack = { screen = Screen.HOME }
            )

            Screen.METADATA -> MetadataScreen(
                onBack = { screen = Screen.HOME }
            )
        }
    }
}