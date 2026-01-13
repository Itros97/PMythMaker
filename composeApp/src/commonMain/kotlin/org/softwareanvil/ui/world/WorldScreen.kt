package org.softwareanvil.ui.world

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun WorldScreen(viewModel: WorldViewModel) {
    val countries by viewModel.countries.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                viewModel.generateWorld(Random.nextLong())
            }
        ) {
            Text("Generar mundo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        countries.forEach { country ->
            val yearText = country.foundationYear
                ?.let { "año $it" }
                ?: "fecha desconocida"

            Text("🌍 ${country.name} ($yearText)")
        }
    }
}
