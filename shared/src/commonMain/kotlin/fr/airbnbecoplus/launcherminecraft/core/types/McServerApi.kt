package fr.airbnbecoplus.launcherminecraft.core.types

import fr.airbnbecoplus.launcherminecraft.core.http.server.AbstractServerDownloader
import fr.airbnbecoplus.launcherminecraft.core.http.server.PaperDownloader

enum class McServerApi {
    BUKKIT, PAPER, SPIGOT;

    fun createDownloader(): AbstractServerDownloader = when (this) {
        BUKKIT -> PaperDownloader()
        PAPER -> PaperDownloader()
        SPIGOT -> PaperDownloader()
    }
}