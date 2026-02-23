package org.softwareanvil.ui.library.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.softwareanvil.ui.world.WorldViewModel

@Composable
fun CharacterDetailScreen(
    viewModel: WorldViewModel,
    onBack: () -> Unit
) {
    val selectedCharacter by viewModel.selectedCharacter.collectAsState()

    Column(Modifier.padding(16.dp)) {
        Button(onClick = onBack) {
            Text("⬅ Volver")
        }

        Spacer(Modifier.height(16.dp))

        selectedCharacter?.let { character ->
            Text("Editando: ${character.firstName} ${character.lastName}")

            // TODO: Aquí va tu formulario de edición
            // Similar a CountryDetailScreen
        } ?: run {
            Text("No hay personaje seleccionado")
        }
    }
}