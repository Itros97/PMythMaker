package org.softwareanvil.ui.library.country

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.softwareanvil.domain.models.Country
import org.softwareanvil.ui.dialog.ConfirmDeleteAllDialog
import org.softwareanvil.ui.dialog.ConfirmDeleteUnitaryDialog
import org.softwareanvil.ui.world.WorldViewModel

@Composable
fun CountryLibraryScreen(
    viewModel: WorldViewModel,
    onBack: () -> Unit,
    onEdit: () -> Unit
) {
    val countries by viewModel.countries.collectAsState()

    var countryToDelete by remember { mutableStateOf<Country?>(null) }
    var showDeleteAllDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadCountries()
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
            Text("🗑️ Borrar todos los países")
        }

        Spacer(Modifier.height(16.dp))

        if (countries.isEmpty()) {
            Text("📭 No hay países guardados")
        } else {
            countries.forEach { country ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text("🌍 ${country.name}")

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                        // ✏️ Editar
                        OutlinedButton(
                            onClick = {
                                viewModel.selectCountry(country)
                                onEdit()
                            }
                        ) {
                            Text("✏️")
                        }

                        // ❌ Borrar → abre popup
                        OutlinedButton(
                            onClick = { countryToDelete = country }
                        ) {
                            Text("🗑️")
                        }
                    }
                }
            }
        }
    }

    countryToDelete?.let { country ->
        ConfirmDeleteUnitaryDialog(
            itemType = "país",
            itemName = country.name,
            onConfirm = {
                viewModel.deleteCountry(country)
                countryToDelete = null
            },
            onDismiss = {
                countryToDelete = null
            }
        )
    }

    if (showDeleteAllDialog) {
        ConfirmDeleteAllDialog(
            itemType = "Countrys",
            onConfirm = {
                viewModel.deleteAllCountries()
                showDeleteAllDialog = false
            },
            onDismiss = {
                showDeleteAllDialog = false
            }
        )
    }
}