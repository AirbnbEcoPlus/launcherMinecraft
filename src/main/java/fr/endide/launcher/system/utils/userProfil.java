package fr.endide.launcher.system.utils;

import javafx.concurrent.Service;

import java.util.List;

public class userProfil {
    public boolean setupIsFinish;
    public String email;
    public String password;
    public boolean startupPassword;
    public List<minecraftUserItem> minecraftUserItems;
    public List<minecraftServer> minecraftServersItems;
    public List<minecraftVersions> minecraftVersionsItems;
    public userProfil(boolean setupIsFinish, String email, String password, boolean startupPassword,List<minecraftUserItem> minecraftUserItems, List<minecraftServer> minecraftServersItems, List<minecraftVersions> minecraftVersionsItems) {
        this.setupIsFinish = setupIsFinish;
        this.email = email;
        this.password = password;
        this.startupPassword = startupPassword;
        this.minecraftUserItems = minecraftUserItems;
        this.minecraftServersItems = minecraftServersItems;
        this.minecraftVersionsItems = minecraftVersionsItems;
    }

    public static class minecraftUserItem {
        public String email;
        public String username;
        public String token;
        public String uuid;
        public boolean isPremium;

        public minecraftUserItem(String email, String username, String token, String uuid, boolean isPremium) {
             this.email = email;
             this.username = username;
             this.token = token;
             this.uuid = uuid;
             this.isPremium = isPremium;

        }
    }

    public static class minecraftVersions {
        public String name;
        public String version;
        public String type;
        public String folder;

        public minecraftVersions(String name, String version, String type, String folder) {
            this.name = name;
            this.version = version;
            this.type = type;
            this.folder = folder;
        }
    }
    public static class minecraftServer{
        public String name;
        public String ram;
        public String api;
        public String version;
        public String path;
        public boolean created;

        public minecraftServer(String name, String ram, String api, String version, String path, boolean created){
            this.name = name;
            this.ram = ram;
            this.api = api;
            this.version = version;
            this.path = path;
            this.created = created;
        }
    }

}
