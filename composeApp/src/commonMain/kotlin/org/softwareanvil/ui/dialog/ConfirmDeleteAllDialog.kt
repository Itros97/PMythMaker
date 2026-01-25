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
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    var confirmationText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("⚠️ Eliminación total") },
        text = {
            Column {
                Text(
                    "Vas a borrar TODOS los países.\n\n" +
                            "Esta acción es irreversible.\n\n" +
                            "Escribe CONFIRMAR para continuar."
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = confirmationText,
                    onValueChange = { confirmationText = it.uppercase() },
                    label = { Text("Escribe CONFIRMAR") },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                enabled = confirmationText == "CONFIRMAR",
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Borrar todo")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
