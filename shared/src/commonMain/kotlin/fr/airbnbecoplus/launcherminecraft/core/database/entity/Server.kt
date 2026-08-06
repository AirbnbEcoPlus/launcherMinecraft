package fr.airbnbecoplus.launcherminecraft.core.database.entity

import fr.airbnbecoplus.launcherminecraft.core.types.McServerApi
import org.jetbrains.exposed.v1.core.Table

object Servers : Table("servers") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val version = integer("version")
    val mcserverapi = enumeration("mcserverapi", McServerApi::class)
}



