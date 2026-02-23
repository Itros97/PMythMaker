package org.softwareanvil.ui.dialog

import androidx.compose.runtime.Composable

@Composable
fun ConfirmDeleteUnitaryDialog(
    itemType: String,
    itemName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    ConfirmDialog(
        title = "⚠️ Eliminar $itemType",
        message = "Vas a borrar el $itemType «$itemName».\n\nEsta acción no se puede deshacer.",
        confirmText = "Borrar",
        onConfirm = onConfirm,
        onDismiss = onDismiss
    )
}