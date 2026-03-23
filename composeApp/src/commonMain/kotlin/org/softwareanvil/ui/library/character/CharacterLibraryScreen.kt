package org.softwareanvil.ui.library.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.softwareanvil.domain.models.Character
import org.softwareanvil.ui.dialog.ConfirmDeleteAllDialog
import org.softwareanvil.ui.dialog.ConfirmDeleteUnitaryDialog
import org.softwareanvil.ui.world.WorldViewModel

@OptIn(ExperimentalMaterial3Api::class)
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Biblioteca de Personajes") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("← Volver")
                    }
                },
                actions = {
                    if (characters.isNotEmpty()) {
                        TextButton(onClick = { showDeleteAllDialog = true }) {
                            Text("Borrar todos")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        if (characters.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "No hay personajes guardados",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Genera uno desde el generador de personajes",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(characters, key = { "${it.firstName}_${it.lastName}_${it.age}" }) { character ->
                    CharacterListItem(
                        character = character,
                        onClick = {
                            viewModel.selectCharacter(character)
                            onEdit()
                        },
                        onDelete = { characterToDelete = character }
                    )
                }
            }
        }
    }

    // ── Diálogos ─────────────────────────────────
    characterToDelete?.let { character ->
        ConfirmDeleteUnitaryDialog(
            itemType = "personaje",
            itemName = "${character.firstName} ${character.lastName}",
            onConfirm = {
                viewModel.deleteCharacter(character)
                viewModel.loadCharacters()
                characterToDelete = null
            },
            onDismiss = { characterToDelete = null }
        )
    }

    if (showDeleteAllDialog) {
        ConfirmDeleteAllDialog(
            itemType = "personajes",
            onConfirm = {
                //TODO: Implement deleteAllCharacters() in the ViewModel
                showDeleteAllDialog = false
            },
            onDismiss = { showDeleteAllDialog = false }
        )
    }
}

@Composable
private fun CharacterListItem(
    character: Character,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Avatar con inicial
            Surface(
                modifier = Modifier.size(48.dp),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = character.firstName.take(1).uppercase(),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            // Info
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "${character.firstName} ${character.lastName}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    character.age?.let {
                        Text(
                            text = "$it años",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    character.country?.let {
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                character.occupation?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Botón borrar
            OutlinedButton(
                onClick = onDelete,
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.size(40.dp)
            ) {
                Text("✕")
            }
        }
    }
}