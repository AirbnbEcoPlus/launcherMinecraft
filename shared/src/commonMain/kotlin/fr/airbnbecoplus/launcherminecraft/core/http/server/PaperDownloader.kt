package fr.airbnbecoplus.launcherminecraft.core.http.server

import java.io.File

class PaperDownloader : AbstractServerDownloader() {

    override val buildRequired: Boolean
        get() = false


    override suspend fun download(version: String, targetDirectory: File): File {
        TODO("Not yet implemented")
    }

    override suspend fun build(version: String, targetDirectory: File): File {
        TODO("Not yet implemented")
    }

}