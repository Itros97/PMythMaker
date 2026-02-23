package org.softwareanvil.ui.library.character

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
    val selectedCharacter by viewModel.selectedCharacter.collectAsState()

    // Estados del formulario
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var occupation by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // Inicializar campos cuando se carga el personaje
    LaunchedEffect(selectedCharacter) {
        selectedCharacter?.let { character ->
            firstName = character.firstName
            lastName = character.lastName
            age = character.age?.toString() ?: ""
            occupation = character.occupation ?: ""
            description = character.description ?: ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // ─────────────────────────────────────────────────────────
        // HEADER
        // ─────────────────────────────────────────────────────────

        Button(onClick = onBack) {
            Text("⬅ Volver")
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "✏️ Editar Personaje",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Modifica los datos del personaje",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(16.dp))

        // ─────────────────────────────────────────────────────────
        // CONTENIDO
        // ─────────────────────────────────────────────────────────

        if (selectedCharacter == null) {
            EmptyState()
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    // Icono
                    Text(
                        text = "👤",
                        style = MaterialTheme.typography.displayMedium
                    )

                    Spacer(Modifier.height(8.dp))

                    // Nombre
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    // Apellido
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text("Apellido") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    // Edad
                    OutlinedTextField(
                        value = age,
                        onValueChange = { age = it.filter { char -> char.isDigit() } },
                        label = { Text("Edad") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    // País (solo lectura)
                    selectedCharacter?.country?.let { country ->
                        OutlinedTextField(
                            value = country.name,
                            onValueChange = {},
                            label = { Text("País") },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = false,
                            singleLine = true
                        )
                    }

                    // Ocupación
                    OutlinedTextField(
                        value = occupation,
                        onValueChange = { occupation = it },
                        label = { Text("Ocupación (opcional)") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    // Descripción
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Descripción (opcional)") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3,
                        maxLines = 5
                    )
                }
            }

            // ─────────────────────────────────────────────────────────
            // BOTONES DE ACCIÓN
            // ─────────────────────────────────────────────────────────

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancelar")
                }

                Button(
                    onClick = {
                        selectedCharacter?.let { character ->
                            val updatedCharacter = character.copy(
                                firstName = firstName,
                                lastName = lastName,
                                age = age.toIntOrNull(),
                                occupation = occupation.ifBlank { null },
                                description = description.ifBlank { null }
                            )
                            viewModel.updateSelectedCharacter(updatedCharacter)
                            onBack()
                        }
                    },
                    modifier = Modifier.weight(1f),
                    enabled = firstName.isNotBlank() && lastName.isNotBlank()
                ) {
                    Text("💾 Guardar")
                }
            }
        }
    }
}

// ═════════════════════════════════════════════════════════════
// COMPONENTE PRIVADO - Estado vacío
// ═════════════════════════════════════════════════════════════

@Composable
private fun EmptyState() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "❌",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.error.copy(alpha = 0.5f)
                )
                Text(
                    text = "No hay personaje seleccionado",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}