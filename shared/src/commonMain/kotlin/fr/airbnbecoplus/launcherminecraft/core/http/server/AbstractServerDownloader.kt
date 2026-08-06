package fr.airbnbecoplus.launcherminecraft.core.http.server

import java.io.File

abstract class AbstractServerDownloader {

    abstract val buildRequired : Boolean;


    /**
     * Télécharge le fichier JAR du serveur.
     * @param version Version de Minecraft (ex: "1.20.4")
     * @param targetDirectory Dossier de destination du fichier
     * @return Le fichier JAR téléchargé
     */
    abstract suspend fun download(version: String, targetDirectory: File): File

    /**
     * Build le fichier JAR du serveur.
     * @param version Version de Minecraft (ex: "1.20.4")
     * @param targetDirectory Dossier de destination du fichier
     * @return Le fichier JAR téléchargé
     */
    abstract suspend fun build(version: String, targetDirectory: File): File
}