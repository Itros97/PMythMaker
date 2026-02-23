package org.softwareanvil.ui.library

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.softwareanvil.ui.components.MenuOptionCard

@Composable
fun LibraryMenuScreen(
    onBack: () -> Unit,
    onCountriesClick: () -> Unit,
    onCharactersClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Button(onClick = onBack) {
            Text("⬅ Volver")
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "📚 Bibliotecas",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Selecciona qué biblioteca quieres ver",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(16.dp))

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
    }
}