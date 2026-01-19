package org.softwareanvil.ui.library.country

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.softwareanvil.ui.world.WorldViewModel

@Composable
fun CountryDetailScreen(
    viewModel: WorldViewModel,
    onBack: () -> Unit
) {
    val country by viewModel.selectedCountry.collectAsState()

    if (country == null) {
        onBack()
        return
    }

    var name by remember { mutableStateOf(country!!.name) }
    var description by remember { mutableStateOf(country!!.description ?: "") }
    var foundationYear by remember {
        mutableStateOf(country!!.foundationYear?.toString() ?: "")
    }

    Column(Modifier.padding(16.dp)) {

        Text("✏️ Editar país")

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") }
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") }
        )

        OutlinedTextField(
            value = foundationYear,
            onValueChange = { foundationYear = it },
            label = { Text("Año de fundación") }
        )

        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                viewModel.editCountry(
                    country!!.copy(
                        name = name,
                        description = description.ifBlank { null },
                        foundationYear = foundationYear.toIntOrNull()
                    )
                )
                onBack()
            }) {
                Text("💾 Guardar")
            }

            Button(onClick = {
                viewModel.clearSelection()
                onBack()
            }) {
                Text("⬅ Volver")
            }
        }
    }
}
