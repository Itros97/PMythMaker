package org.softwareanvil.ui.metadata

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MetadataScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Info") },
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))

            // ── Logo ─────────────────────────────
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

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Pocket Mythsmith",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    InfoRow(label = "Versión", value = "1.0.0")
                    InfoRow(label = "Plataforma", value = "Compose Multiplatform")
                    InfoRow(label = "Desarrollador", value = "Software Anvil")
                    InfoRow(label = "Web", value = "softwareanvil.me")
                }
            }

            // ── Tecnologías ──────────────────────
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Tecnologías",
                        style = MaterialTheme.typography.titleMedium
                    )

                    InfoRow(label = "UI", value = "Jetpack Compose / Material 3")
                    InfoRow(label = "Lenguaje", value = "Kotlin Multiplatform")
                    InfoRow(label = "Arquitectura", value = "MVVM")
                }
            }

            // ── Licencia ─────────────────────────
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Licencia",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Este software es propiedad de Software Anvil. Todos los derechos reservados.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}