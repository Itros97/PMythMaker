package org.softwareanvil.ui.generator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun GenerateMenuScreen(
    onGenerateCountries: () -> Unit,
    onGenerateCharacters: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text("⬅ Volver")
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "✨ Generar",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onGenerateCountries,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🌍 Generar países")
        }

        Button(
            onClick = onGenerateCharacters,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🧑 Generar personajes")
        }

        OutlinedButton(
            onClick = {},
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🛐 Religiones (próximamente)")
        }

        OutlinedButton(
            onClick = {},
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("📜 Eventos históricos (próximamente)")
        }
    }
}
