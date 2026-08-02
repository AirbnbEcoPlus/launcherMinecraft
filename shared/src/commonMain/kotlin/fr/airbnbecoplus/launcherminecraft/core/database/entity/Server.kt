package fr.airbnbecoplus.launcherminecraft.core.database.entity

import org.jetbrains.exposed.v1.core.Table

object Servers : Table("servers") {
    val id = integer("id").autoIncrement();

}



