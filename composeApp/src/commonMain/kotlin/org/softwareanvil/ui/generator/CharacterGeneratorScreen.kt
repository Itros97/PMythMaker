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
import org.softwareanvil.ui.character.CharacterCard
import org.softwareanvil.ui.world.WorldViewModel

@Composable
fun CharacterGeneratorScreen(
    viewModel: WorldViewModel,
    onBack: () -> Unit
) {
    val character by viewModel.generatedCharacter.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ⬅ Volver
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
                    text = "Generador de Personajes",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(24.dp))

                if (character == null) {
                    Text(
                        text = "Aún no se ha generado ningún personaje",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                } else {
                    CharacterCard(character!!)
                }

                Spacer(Modifier.height(32.dp))

                // 🔁 Generar
                Button(
                    onClick = { viewModel.generateCharacter() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("🔁 Generar personaje")
                }

                Spacer(Modifier.height(12.dp))

                // 💾 Guardar / 🗑️ Descartar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Button(
                        onClick = { viewModel.saveGeneratedCharacter() },
                        enabled = character != null,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("💾 Guardar")
                    }

                    OutlinedButton(
                        onClick = { viewModel.discardGeneratedCharacter() },
                        enabled = character != null,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("🗑️ Descartar")
                    }
                }
            }
        }
    }
}
