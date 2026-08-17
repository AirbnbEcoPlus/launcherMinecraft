package fr.airbnbecoplus.launcherminecraft.ui.components.button

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
internal fun MacosButton(
    onClick: () -> Unit,
    text: String,
) {
    Button(
        onClick = onClick,
    ) {
        Text(text = text)
    }
}