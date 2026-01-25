package org.softwareanvil.ui.library.country

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.softwareanvil.ui.world.WorldViewModel

@Composable
fun CountryDetailScreen(
    viewModel: WorldViewModel,
    onBack: () -> Unit
) {
    val countryState by viewModel.selectedCountry.collectAsState()
    val country = countryState ?: run {
        onBack()
        return
    }

    var name by remember(country) {
        mutableStateOf(country.name)
    }
    var description by remember(country) {
        mutableStateOf(country.description ?: "")
    }
    var foundationYear by remember(country) {
        mutableStateOf(country.foundationYear?.toString() ?: "")
    }
    var motto by remember(country) {
        mutableStateOf(country.motto ?: "")
    }

    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🎴 Card principal (ficha)
        Card(
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 🌍 Título
                Text(
                    text = "🌍 Detalle del país",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(20.dp))

                // 🌍 Nombre
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = foundationYear,
                    onValueChange = { input ->
                        foundationYear = input.filter(Char::isDigit)
                    },
                    label = { Text("Año de fundación") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = motto,
                    onValueChange = { motto = it },
                    label = { Text("Lema") },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Ej. «Unidad, Honor y Tierra»") }
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 5
                )

                Spacer(Modifier.height(24.dp))

                // 💾 Acciones
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
                            viewModel.editCountry(
                                country.copy(
                                    name = name.trim(),
                                    description = description.ifBlank { null },
                                    foundationYear = foundationYear.toIntOrNull(),
                                    motto = motto.ifBlank { null }
                                )
                            )
                            onBack()
                        },
                        modifier = Modifier.weight(1f),
                        enabled = name.isNotBlank()
                    ) {
                        Text("Guardar")
                    }
                }
            }
        }
    }
}