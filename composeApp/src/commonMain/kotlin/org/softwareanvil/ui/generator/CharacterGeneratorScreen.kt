package org.softwareanvil.ui.generator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Button(onClick = onBack) {
            Text("⬅ Volver")
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "👤 Generador de Personajes",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Genera personajes aleatorios con nombres únicos",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(16.dp))

        // ─────────────────────────────────────────────────────────
        // CARD DE RESULTADO
        // ─────────────────────────────────────────────────────────

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                if (character == null) {
                    EmptyCharacterState()
                } else {
                    CharacterCard(character!!)
                }
            }
        }

        // ─────────────────────────────────────────────────────────
        // ACTION BUTTONS
        // ─────────────────────────────────────────────────────────

        Button(
            onClick = { viewModel.generateCharacter() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🔁 Generar personaje")
        }

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

// ═════════════════════════════════════════════════════════════
// EMPTY STATE
// ═════════════════════════════════════════════════════════════

@Composable
private fun EmptyCharacterState() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "👤",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
        )

        Text(
            text = "Aún no se ha generado ningún personaje",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = "Pulsa el botón de abajo para generar uno",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
        )
    }
}