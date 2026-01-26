package org.softwareanvil

import androidx.compose.runtime.*
import org.softwareanvil.ui.Screen
import org.softwareanvil.ui.generator.GenerateMenuScreen
import org.softwareanvil.ui.generator.GeneratorScreen
import org.softwareanvil.ui.home.HomeScreen
import org.softwareanvil.ui.library.LibraryScreen
import org.softwareanvil.ui.library.country.CountryDetailScreen
import org.softwareanvil.ui.placeholder.ComingSoonScreen

@Composable
fun App() {
    val viewModel = remember {
        WorldFactory.createWorldViewModel()
    }

    var screen by remember { mutableStateOf(Screen.HOME) }

    when (screen) {

        Screen.HOME -> HomeScreen(
            onGenerate = { screen = Screen.GENERATE_MENU },
            onLibrary = { screen = Screen.LIBRARY }
        )

        Screen.GENERATE_MENU -> GenerateMenuScreen(
            onGenerateCountries = { screen = Screen.GENERATE_COUNTRIES },
            onGenerateCharacters = { screen = Screen.GENERATE_CHARACTERS },
            onBack = { screen = Screen.HOME }
        )

        Screen.GENERATE_COUNTRIES -> GeneratorScreen(
            viewModel = viewModel,
            onBack = { screen = Screen.GENERATE_MENU }
        )

        Screen.GENERATE_CHARACTERS -> {
            ComingSoonScreen(
                title = "Generador de personajes",
                onBack = { screen = Screen.GENERATE_MENU }
            )
        }

        Screen.LIBRARY -> LibraryScreen(
            viewModel = viewModel,
            onBack = { screen = Screen.HOME },
            onEdit = { screen = Screen.COUNTRY_DETAIL }
        )

        Screen.COUNTRY_DETAIL -> CountryDetailScreen(
            viewModel = viewModel,
            onBack = { screen = Screen.LIBRARY }
        )
    }

}
