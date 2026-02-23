package org.softwareanvil.ui.library.character

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.softwareanvil.domain.models.Character
import org.softwareanvil.ui.dialog.ConfirmDeleteAllDialog
import org.softwareanvil.ui.dialog.ConfirmDeleteUnitaryDialog
import org.softwareanvil.ui.world.WorldViewModel

@Composable
fun CharacterLibraryScreen(
    viewModel: WorldViewModel,
    onBack: () -> Unit,
    onEdit: () -> Unit
) {
    val characters by viewModel.characters.collectAsState()

    var characterToDelete by remember { mutableStateOf<Character?>(null) }
    var showDeleteAllDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadCharacters()
    }

    Column(Modifier.padding(16.dp)) {

        Button(onClick = onBack) {
            Text("⬅ Volver")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { showDeleteAllDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🗑️ Borrar todos los personajes")
        }

        Spacer(Modifier.height(16.dp))

        if (characters.isEmpty()) {
            Text("📭 No hay personajes guardados")
        } else {
            characters.forEach { character ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {
                        Text("👤 ${character.firstName} ${character.lastName}")
                        character.country?.let { country ->
                            Text(
                                "🌍 ${country.name}",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Text(
                            "🎂 ${character.age} años",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                        // ✏️ Editar
                        OutlinedButton(
                            onClick = {
                                viewModel.selectCharacter(character)
                                onEdit()
                            }
                        ) {
                            Text("✏️")
                        }

                        // ❌ Borrar → abre popup
                        OutlinedButton(
                            onClick = { characterToDelete = character }
                        ) {
                            Text("🗑️")
                        }
                    }
                }
            }
        }
    }

    characterToDelete?.let { character ->
        ConfirmDeleteUnitaryDialog(
            itemType = "personaje",
            itemName = "${character.firstName} ${character.lastName}",
            onConfirm = {
                viewModel.deleteCharacter(character)
                viewModel.loadCharacters()
                characterToDelete = null
            },
            onDismiss = {
                characterToDelete = null
            }
        )
    }

    if (showDeleteAllDialog) {
        ConfirmDeleteAllDialog(
            itemType = "personajes",
            onConfirm = {
                //TODO: Implement deleteAllCharacters() in the ViewModel
                //viewModel.deleteAllCountries()
                showDeleteAllDialog = false
            },
            onDismiss = {
                showDeleteAllDialog = false
            }
        )
    }
}