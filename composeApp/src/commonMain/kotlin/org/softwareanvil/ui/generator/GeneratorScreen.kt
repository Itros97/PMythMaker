package org.softwareanvil.ui.generator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.softwareanvil.domain.models.Country
import org.softwareanvil.ui.world.WorldViewModel

@Composable
fun GeneratorScreen(
    viewModel: WorldViewModel,
    onBack: () -> Unit
) {
    val currentCountry: Country? by viewModel
        .generatedCountry
        .collectAsState(initial = null)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text("⬅ Volver")
        }

        Spacer(Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "🌍 Generador de Países",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(24.dp))

                if (currentCountry == null) {
                    Text(
                        text = "Aún no se ha generado ningún país",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                } else {

                    Text(
                        text = currentCountry!!.name,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )

                    currentCountry!!.foundationYear?.let { year ->
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Año de fundación: $year",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    // 📜 Descripción
                    currentCountry!!.description?.let { description ->
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(Modifier.height(32.dp))

                // 🔁 Generar
                Button(
                    onClick = { viewModel.generateCountry() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("🔁 Generar nuevo país")
                }

                Spacer(Modifier.height(12.dp))

                // 💾 Guardar / 🗑️ Descartar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { viewModel.saveGeneratedCountry() },
                        enabled = currentCountry != null,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("💾 Guardar")
                    }

                    OutlinedButton(
                        onClick = { viewModel.discardGeneratedCountry() },
                        enabled = currentCountry != null,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("🗑️ Descartar")
                    }
                }
            }
        }
    }
}
