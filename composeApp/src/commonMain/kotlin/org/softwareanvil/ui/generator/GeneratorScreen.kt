package org.softwareanvil.ui.generator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.softwareanvil.ui.world.WorldViewModel

@Composable
fun GeneratorScreen(
    viewModel: WorldViewModel,
    onBack: () -> Unit
) {
    val countries by viewModel.countries.collectAsState()

    Column(Modifier.padding(16.dp)) {

        // 🔝 Barra superior
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { viewModel.generateOne() }) {
                Text("➕ Generar país")
            }

            Button(onClick = { viewModel.saveAll() }) {
                Text("💾💾 Guardar todos")
            }

            Button(onClick = onBack) {
                Text("⬅ Volver")
            }
        }

        Spacer(Modifier.height(16.dp))

        // 📋 Lista de países generados
        countries.forEach { country ->
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "🌍 ${country.name}",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "🌍 ${country.foundationYear}",
                    modifier = Modifier.weight(1f)
                )

             //   Button(onClick = { viewModel.save(country) }) {
                    Text("💾")
                }

             //   Button(onClick = { viewModel.removeFromMemory(country) }) {
                    Text("❌")
                }
            }
        }
