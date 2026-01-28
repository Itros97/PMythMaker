package org.softwareanvil.ui.character

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.softwareanvil.ui.world.WorldViewModel

@Composable
fun CharacterDetailScreen(
    viewModel: WorldViewModel,
    onBack: () -> Unit
) {
    val character by viewModel.selectedCharacter.collectAsState()

    if (character == null) {
        onBack()
        return
    }

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

        Spacer(Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CharacterCard(character = character!!)

                Spacer(Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(onClick = {
                        // futuro: editar personaje
                    }) {
                        Text("✏️ Editar")
                    }

                    OutlinedButton(onClick = {
                        viewModel.deleteCharacter(character!!)
                        onBack()
                    }) {
                        Text("🗑️ Borrar")
                    }
                }
            }
        }
    }
}
