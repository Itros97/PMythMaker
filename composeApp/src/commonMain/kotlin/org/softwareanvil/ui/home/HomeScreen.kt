package org.softwareanvil.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onGenerator: () -> Unit,
    onLibrary: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HomeCell(
                text = "🌍\nGenerar países",
                modifier = Modifier.weight(1f),
                onClick = onGenerator
            )
            HomeCell(
                text = "📚\nBiblioteca",
                modifier = Modifier.weight(1f),
                onClick = onLibrary
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HomeCell(
                text = "⬜\nPróximamente",
                modifier = Modifier.weight(1f),
                onClick = {}
            )
            HomeCell(
                text = "⬜\nPróximamente",
                modifier = Modifier.weight(1f),
                onClick = {}
            )
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

