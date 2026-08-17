package fr.airbnbecoplus.launcherminecraft.ui.components.button

import androidx.compose.runtime.Composable
import fr.airbnbecoplus.launcherminecraft.core.utils.OperatingSystem
import fr.airbnbecoplus.launcherminecraft.core.utils.currentOS

@Composable
fun AdaptiveButton(onClick: () -> Unit, text: String) {
    when (currentOS) {
        OperatingSystem.WINDOWS -> WindowsButton(onClick, text)
        OperatingSystem.MACOS -> MacosButton(onClick, text)
        else -> GenericButton(onClick, text)
    }
}