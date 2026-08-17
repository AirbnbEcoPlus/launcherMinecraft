package fr.airbnbecoplus.launcherminecraft.ui.components.button

import androidx.compose.runtime.Composable
import io.github.composefluent.component.Button
import io.github.composefluent.component.Text

@Composable
internal fun WindowsButton(onClick: () -> Unit, text: String) {
    Button(onClick = onClick) {
        Text(text = text);
    }
}