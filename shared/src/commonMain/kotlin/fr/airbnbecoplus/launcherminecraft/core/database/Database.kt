package fr.airbnbecoplus.launcherminecraft.core.database

class Database private constructor() {
    companion object {

        private var db: Database? = null

        init {
            initialise()
        }

        fun initialise(){

        }


        @Volatile
        private var instance : Database? = null;


        fun getInstance() = instance ?: synchronized(this) {
            instance ?: Database().also { instance = it }
        }
    }


}