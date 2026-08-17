package fr.airbnbecoplus.launcherminecraft.core.utils

import java.io.File

object FileManager {

    val gameDir: File by lazy {
        val userHome = System.getProperty("user.home")
        val folderName = ".endideLauncher"

        val folder = if (currentOS == OperatingSystem.WINDOWS) {
            File(userHome, "AppData/Roaming/$folderName")
        } else {
            File(userHome, folderName)
        }

        if (!folder.exists()) folder.mkdirs()
        folder
    }

    val assetsDir: File get() = File(gameDir, "assets")
    val libsDir: File get() = File(gameDir, "libs")
    val runtimeDir: File get() = File(gameDir, "runtime")
    val serverDir: File get() = File(gameDir, "servers")
    val serverInstallDir: File get() = File(serverDir, "install")

}