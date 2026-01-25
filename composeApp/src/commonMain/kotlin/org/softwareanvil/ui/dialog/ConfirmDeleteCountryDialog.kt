package org.softwareanvil.ui.dialog

import androidx.compose.runtime.Composable

@Composable
fun ConfirmDeleteCountryDialog(
    countryName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    ConfirmDialog(
        title = "⚠️ Eliminar país",
        message = "Vas a borrar el país «$countryName».\n\nEsta acción no se puede deshacer.",
        confirmText = "Borrar",
        onConfirm = onConfirm,
        onDismiss = onDismiss
    )
}
