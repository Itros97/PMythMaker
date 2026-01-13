package org.softwareanvil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.softwareanvil.ui.world.WorldScreen
import org.softwareanvil.ui.world.WorldViewModel

@Composable
fun App() {
    val viewModel: WorldViewModel = remember {
        WorldFactory.createWorldViewModel()
    }

    WorldScreen(viewModel)
}

