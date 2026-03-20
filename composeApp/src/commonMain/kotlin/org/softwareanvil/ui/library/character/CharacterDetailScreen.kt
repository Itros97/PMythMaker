package org.softwareanvil.ui.library.character

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.softwareanvil.ui.character.CharacterCard
import org.softwareanvil.ui.world.WorldViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    viewModel: WorldViewModel,
    onBack: () -> Unit
) {
    val selectedCharacter by viewModel.selectedCharacter.collectAsState()

    if (selectedCharacter == null) {
        LaunchedEffect(Unit) { onBack() }
        return
    }

    val character = selectedCharacter!!
    var isEditMode by remember { mutableStateOf(true) }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var occupation by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    LaunchedEffect(character) {
        firstName = character.firstName
        lastName = character.lastName
        age = character.age?.toString() ?: ""
        occupation = character.occupation ?: ""
        description = character.description ?: ""
    }

    fun resetFields() {
        firstName = character.firstName
        lastName = character.lastName
        age = character.age?.toString() ?: ""
        occupation = character.occupation ?: ""
        description = character.description ?: ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(if (isEditMode) "Editar Personaje" else "Detalles del Personaje")
                },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("← Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = if (isEditMode) "Modifica los datos del personaje"
                else "Información del personaje",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                if (isEditMode) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedTextField(
                                value = firstName,
                                onValueChange = { firstName = it },
                                label = { Text("Nombre") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = lastName,
                                onValueChange = { lastName = it },
                                label = { Text("Apellido") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedTextField(
                                value = age,
                                onValueChange = { age = it.filter(Char::isDigit) },
                                label = { Text("Edad") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = character.country?.name ?: "Sin país",
                                onValueChange = {},
                                label = { Text("País") },
                                modifier = Modifier.weight(1f),
                                enabled = false,
                                singleLine = true
                            )
                        }

                        OutlinedTextField(
                            value = occupation,
                            onValueChange = { occupation = it },
                            label = { Text("Ocupación (opcional)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = description,
                            onValueChange = { description = it },
                            label = { Text("Descripción (opcional)") },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 3,
                            maxLines = 5
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CharacterCard(character = character)
                    }
                }
            }

            if (isEditMode) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            resetFields()
                            isEditMode = false
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancelar")
                    }

                    Button(
                        onClick = {
                            val updated = character.copy(
                                firstName = firstName,
                                lastName = lastName,
                                age = age.toIntOrNull(),
                                occupation = occupation.ifBlank { null },
                                description = description.ifBlank { null }
                            )
                            viewModel.updateSelectedCharacter(updated)
                            isEditMode = false
                        },
                        modifier = Modifier.weight(1f),
                        enabled = firstName.isNotBlank() && lastName.isNotBlank()
                    ) {
                        Text("Guardar")
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { isEditMode = true },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Editar")
                    }

                    OutlinedButton(
                        onClick = {
                            viewModel.deleteCharacter(character)
                            onBack()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Borrar")
                    }
                }
            }
        }
    }
}