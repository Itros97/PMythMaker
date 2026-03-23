package org.softwareanvil.ui.library.country

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.softwareanvil.domain.models.Country
import org.softwareanvil.ui.dialog.ConfirmDeleteAllDialog
import org.softwareanvil.ui.dialog.ConfirmDeleteUnitaryDialog
import org.softwareanvil.ui.world.WorldViewModel

@OptIn(ExperimentalMaterial3Api::class)
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Biblioteca de Países") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("← Volver")
                    }
                },
                actions = {
                    if (countries.isNotEmpty()) {
                        TextButton(onClick = { showDeleteAllDialog = true }) {
                            Text("Borrar todos")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        if (countries.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "No hay países guardados",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Genera uno desde el generador de países",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(countries, key = { it.name }) { country ->
                    CountryListItem(
                        country = country,
                        onClick = {
                            viewModel.selectCountry(country)
                            onEdit()
                        },
                        onDelete = { countryToDelete = country }
                    )
                }
            }
        }
    }

    // ── Diálogos ─────────────────────────────────
    countryToDelete?.let { country ->
        ConfirmDeleteUnitaryDialog(
            itemType = "país",
            itemName = country.name,
            onConfirm = {
                viewModel.deleteCountry(country)
                countryToDelete = null
            },
            onDismiss = { countryToDelete = null }
        )
    }

    if (showDeleteAllDialog) {
        ConfirmDeleteAllDialog(
            itemType = "países",
            onConfirm = {
                viewModel.deleteAllCountries()
                showDeleteAllDialog = false
            },
            onDismiss = { showDeleteAllDialog = false }
        )
    }
}

@Composable
private fun CountryListItem(
    country: Country,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Avatar con inicial
            Surface(
                modifier = Modifier.size(48.dp),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = country.name.take(1).uppercase(),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            // Info del país
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = country.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                country.foundationYear?.let {
                    Text(
                        text = "Fundado en $it",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Botón borrar
            OutlinedButton(
                onClick = onDelete,
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.size(40.dp)
            ) {
                Text("✕")
            }
        }
    }
}