package org.softwareanvil.ui.library

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.softwareanvil.ui.components.MenuOptionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryMenuScreen(
    onBack: () -> Unit,
    onCountriesClick: () -> Unit,
    onCharactersClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bibliotecas") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("← Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Selecciona qué biblioteca quieres ver",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            MenuOptionCard(
                icon = "🌍",
                title = "Países",
                description = "Ver y gestionar países guardados",
                onClick = onCountriesClick
            )

            MenuOptionCard(
                icon = "👤",
                title = "Personajes",
                description = "Ver y gestionar personajes guardados",
                onClick = onCharactersClick
            )

            Spacer(Modifier.height(32.dp))
        }
    }
}