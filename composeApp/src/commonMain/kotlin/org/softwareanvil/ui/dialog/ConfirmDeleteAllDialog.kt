package org.softwareanvil.ui.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmDeleteAllDialog(
    itemType: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    var confirmationText by remember { mutableStateOf("") }
    val isConfirmed = confirmationText == CONFIRMATION_WORD

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("⚠️ Eliminación total") },
        text = {
            Column {
                Text(buildConfirmationMessage(itemType))

                Spacer(Modifier.height(12.dp))

                ConfirmationTextField(
                    value = confirmationText,
                    onValueChange = { confirmationText = it.uppercase() }
                )
            }
        },
        confirmButton = {
            DangerButton(
                text = "Borrar todo",
                enabled = isConfirmed,
                onClick = onConfirm
            )
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

private const val CONFIRMATION_WORD = "CONFIRMAR"

private fun buildConfirmationMessage(itemType: String): String =
    "Vas a borrar TODOS los $itemType.\n\n" +
            "Esta acción es irreversible.\n\n" +
            "Escribe $CONFIRMATION_WORD para continuar."

@Composable
private fun ConfirmationTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Escribe $CONFIRMATION_WORD") },
        singleLine = true
    )
}

@Composable
private fun DangerButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error
        )
    ) {
        Text(text)
    }
}