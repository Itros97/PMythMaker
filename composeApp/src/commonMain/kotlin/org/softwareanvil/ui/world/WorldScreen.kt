package org.softwareanvil.ui.world

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WorldScreen(viewModel: WorldViewModel) {
    val countries by viewModel.countries.collectAsState()

    Column(Modifier.padding(16.dp)) {

        Button(onClick = { viewModel.generateOne() }) {
            Text("➕ Generar país")
        }

        Button(onClick = { viewModel.saveGenerated() }) {
            Text("💾 Guardar")
        }

        Spacer(Modifier.height(16.dp))

        countries.forEach { country ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("🌍 ${country.name}")
                Button(onClick = { viewModel.delete(country) }) {
                    Text("❌")
                }
            }
        }
    }
}

