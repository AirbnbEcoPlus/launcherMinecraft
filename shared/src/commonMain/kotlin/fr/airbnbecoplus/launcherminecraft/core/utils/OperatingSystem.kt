package fr.airbnbecoplus.launcherminecraft.core.utils

enum class OperatingSystem {
    WINDOWS, MACOS, LINUX, UNKNOWN
}

val currentOS: OperatingSystem by lazy {
    val osName = System.getProperty("os.name").lowercase()
    when {
        osName.contains("win") -> OperatingSystem.WINDOWS
        osName.contains("mac") -> OperatingSystem.MACOS
        osName.contains("nix") || osName.contains("nux") || osName.contains("aix") -> OperatingSystem.LINUX
        else -> OperatingSystem.UNKNOWN
    }
}