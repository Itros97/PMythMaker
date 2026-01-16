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

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { viewModel.generateOne() }) {
                Text("➕ Generar país")
            }

            Button(onClick = onBack) {
                Text("⬅ Volver")
            }
        }

        Spacer(Modifier.height(16.dp))

        countries.forEach {
            Text("🌍 ${it.name}")
        }
    }
}
