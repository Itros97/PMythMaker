package org.softwareanvil.ui.generator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.softwareanvil.domain.models.Country
import org.softwareanvil.ui.character.CharacterCard
import org.softwareanvil.ui.world.WorldViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterGeneratorScreen(
    viewModel: WorldViewModel,
    onBack: () -> Unit
) {
    val character by viewModel.generatedCharacter.collectAsState()
    val countries by viewModel.countries.collectAsState()

    var selectedCountry by remember { mutableStateOf<Country?>(null) }
    var countryExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(selectedCountry) {
        viewModel.updateGeneratedCharacterCountry(selectedCountry)
    }

    LaunchedEffect(Unit) {
        viewModel.loadCountries()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Generador de Personajes") },
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Genera personajes aleatorios con nombres únicos",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // ── Selector de país ─────────────────────
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "País (opcional)",
                        style = MaterialTheme.typography.titleSmall
                    )

                    if (countries.isEmpty()) {
                        Text(
                            text = "No hay países guardados. Genera uno primero.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        ExposedDropdownMenuBox(
                            expanded = countryExpanded,
                            onExpandedChange = { countryExpanded = it }
                        ) {
                            OutlinedTextField(
                                value = selectedCountry?.name ?: "Sin país",
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = countryExpanded)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor()
                            )

                            ExposedDropdownMenu(
                                expanded = countryExpanded,
                                onDismissRequest = { countryExpanded = false }
                            ) {
                                // Opción para no asignar país
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
                    }
                }
            }

            // ── Resultado ────────────────────────────
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
                    Text("Guardar")
                }

                OutlinedButton(
                    onClick = { viewModel.discardGeneratedCharacter() },
                    enabled = character != null,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Descartar")
                }
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