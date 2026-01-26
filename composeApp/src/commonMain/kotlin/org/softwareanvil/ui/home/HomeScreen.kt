package org.softwareanvil.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onGenerate: () -> Unit,
    onLibrary: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Pocket Mythsmith",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onGenerate,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("✨ Generar")
        }

        OutlinedButton(
            onClick = onLibrary,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("📚 Biblioteca")
        }
    }
}


@Composable
fun HomeCell(text: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text, textAlign = TextAlign.Center)
    }
}

