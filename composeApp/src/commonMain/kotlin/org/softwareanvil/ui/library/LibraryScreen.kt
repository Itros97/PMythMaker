package org.softwareanvil.ui.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.softwareanvil.domain.models.Country
import org.softwareanvil.ui.world.WorldViewModel

@Composable
fun LibraryScreen(
    viewModel: WorldViewModel,
    onBack: () -> Unit,
    onEdit: () -> Unit
) {
    val countries by viewModel.countries.collectAsState()

    LaunchedEffect(Unit) {
     //   viewModel.loadSaved()
    }

    Column(Modifier.padding(16.dp)) {

        Button(onClick = onBack) {
            Text("⬅ Volver")
        }

        Spacer(Modifier.height(16.dp))

        countries.forEach { country: Country ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("🌍 ${country.name}")

                Button(onClick = {
                    viewModel.selectCountry(country)
                    onEdit()
                }) {
                    Text("✏️")
                }
            }
        }
    }
}
