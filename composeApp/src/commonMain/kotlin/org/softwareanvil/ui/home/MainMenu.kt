package org.softwareanvil.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onGenerate: () -> Unit,
    onLibrary: () -> Unit,
    onSettings: () -> Unit,
    onMetadata: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(1f))

            // ── Logo ─────────────────────────────────
            Text(
                text = """
                    ╔═══════════════════════════╗
                    ║                           ║
                    ║      ┌──┐    /\           ║
                    ║      │  │   /  \          ║
                    ║      │  │  / /\ \         ║
                    ║      └──┘ / ____ \        ║
                    ║          /_/    \_\       ║
                    ║     ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄     ║
                    ║     █ SOFTWARE ANVIL █    ║
                    ║     ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀    ║
                    ║                           ║
                    ╚═══════════════════════════╝
                """.trimIndent(),
                fontFamily = FontFamily.Monospace,
                fontSize = 11.sp,
                lineHeight = 14.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(8.dp))

            // ── Título ───────────────────────────────
            Text(
                text = "Pocket Mythsmith",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Tu forja de mundos de bolsillo",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = onGenerate,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Generar")
            }

            OutlinedButton(
                onClick = onLibrary,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Biblioteca")
            }

            Spacer(Modifier.height(8.dp))

            // ── Acciones secundarias ─────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onSettings,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Ajustes")
                }

                OutlinedButton(
                    onClick = onMetadata,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Info")
                }
            }

            Spacer(Modifier.weight(1f))

            // ── Footer ───────────────────────────────
            Text(
                text = "softwareanvil.me",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                textAlign = TextAlign.Center
            )
        }
    }
}