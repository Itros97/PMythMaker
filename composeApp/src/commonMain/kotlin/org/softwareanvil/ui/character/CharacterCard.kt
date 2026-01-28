package org.softwareanvil.ui.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.softwareanvil.domain.models.Character

@Composable
fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "${character.firstName} ${character.lastName}",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Edad: ${character.age}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        character.country?.let {
            Spacer(Modifier.height(4.dp))
            Text("País: ${it.name}")
        }

        character.occupation?.let {
            Spacer(Modifier.height(4.dp))
            Text("Ocupación: $it")
        }

        character.description?.let {
            Spacer(Modifier.height(8.dp))
            Text(
                text = it,
                textAlign = TextAlign.Center
            )
        }
    }
}
