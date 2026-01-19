package org.softwareanvil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.softwareanvil.ui.Screen
import org.softwareanvil.ui.generator.GeneratorScreen
import org.softwareanvil.ui.home.HomeScreen
import org.softwareanvil.ui.library.LibraryScreen
import org.softwareanvil.ui.library.country.CountryDetailScreen

@Composable
fun App() {
    val viewModel = remember {
        WorldFactory.createWorldViewModel()
    }

    var screen by remember { mutableStateOf(Screen.HOME) }

    when (screen) {
        Screen.HOME -> HomeScreen(
            onGenerator = { screen = Screen.GENERATOR },
            onLibrary = { screen = Screen.LIBRARY }
        )

        Screen.GENERATOR -> GeneratorScreen(
            viewModel = viewModel,
            onBack = { screen = Screen.HOME }
        )

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

