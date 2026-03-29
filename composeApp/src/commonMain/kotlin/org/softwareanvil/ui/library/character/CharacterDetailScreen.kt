package org.softwareanvil.ui.library.character

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.softwareanvil.domain.models.Country
import org.softwareanvil.ui.world.WorldViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    viewModel: WorldViewModel,
    onBack: () -> Unit
) {
    val selectedCharacter by viewModel.selectedCharacter.collectAsState()
    val countries by viewModel.countries.collectAsState()

    if (selectedCharacter == null) {
        LaunchedEffect(Unit) { onBack() }
        return
    }

    val character = selectedCharacter!!
    var isEditing by remember { mutableStateOf(false) }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var occupation by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedCountry by remember { mutableStateOf<Country?>(null) }
    var countryExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadCountries()
    }

    LaunchedEffect(character) {
        firstName = character.firstName
        lastName = character.lastName
        age = character.age?.toString() ?: ""
        occupation = character.occupation ?: ""
        description = character.description ?: ""
        selectedCountry = character.country
    }

    fun resetFields() {
        firstName = character.firstName
        lastName = character.lastName
        age = character.age?.toString() ?: ""
        occupation = character.occupation ?: ""
        description = character.description ?: ""
        selectedCountry = character.country
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles del Personaje") },
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
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
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
                            singleLine = true,
                            enabled = isEditing
                        )
                        OutlinedTextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            label = { Text("Apellido") },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            enabled = isEditing
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
                            singleLine = true,
                            enabled = isEditing
                        )

                        // País: dropdown en edición, texto estático en vista
                        if (isEditing) {
                            ExposedDropdownMenuBox(
                                expanded = countryExpanded,
                                onExpandedChange = { countryExpanded = it },
                                modifier = Modifier.weight(1f)
                            ) {
                                OutlinedTextField(
                                    value = selectedCountry?.name ?: "Sin país",
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text("País") },
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = countryExpanded)
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                    singleLine = true
                                )

                                ExposedDropdownMenu(
                                    expanded = countryExpanded,
                                    onDismissRequest = { countryExpanded = false }
                                ) {
                                    DropdownMenuItem(
                                        text = { Text("Sin país") },
                                        onClick = {
                                            selectedCountry = null
                                            countryExpanded = false
                                        }
                                    )
                                    countries.forEach { country ->
                                        DropdownMenuItem(
                                            text = { Text(country.name) },
                                            onClick = {
                                                selectedCountry = country
                                                countryExpanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        } else {
                            OutlinedTextField(
                                value = selectedCountry?.name ?: "Sin país",
                                onValueChange = {},
                                label = { Text("País") },
                                modifier = Modifier.weight(1f),
                                singleLine = true,
                                enabled = false
                            )
                        }
                    }

                    OutlinedTextField(
                        value = occupation,
                        onValueChange = { occupation = it },
                        label = { Text("Ocupación (opcional)") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        enabled = isEditing
                    )

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Descripción (opcional)") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3,
                        maxLines = 5,
                        enabled = isEditing
                    )
                }
            }

            // ── Botones ──────────────────────────────────
            if (isEditing) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            resetFields()
                            isEditing = false
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
                                country = selectedCountry,
                                occupation = occupation.ifBlank { null },
                                description = description.ifBlank { null }
                            )
                            viewModel.updateSelectedCharacter(updated)
                            isEditing = false
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
                        onClick = { isEditing = true },
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

            Spacer(Modifier.height(32.dp))
        }
    }
}