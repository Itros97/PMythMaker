package org.softwareanvil.ui.generator

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
fun GenerateMenuScreen(
    onGenerateCountries: () -> Unit,
    onGenerateCharacters: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Generadores") },
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
                text = "Selecciona qué quieres generar",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            MenuOptionCard(
                icon = "🌍",
                title = "Países",
                description = "Genera nombres de países aleatorios",
                onClick = onGenerateCountries
            )

            MenuOptionCard(
                icon = "👤",
                title = "Personajes",
                description = "Genera nombres de personajes aleatorios",
                onClick = onGenerateCharacters
            )

            MenuOptionCard(
                icon = "🛐",
                title = "Religiones",
                description = "Próximamente disponible",
                onClick = {},
                enabled = false
            )

            MenuOptionCard(
                icon = "📜",
                title = "Eventos históricos",
                description = "Próximamente disponible",
                onClick = {},
                enabled = false
            )

            Spacer(Modifier.height(32.dp))
        }
    }
}