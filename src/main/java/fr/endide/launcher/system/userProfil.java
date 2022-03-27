package fr.endide.launcher.system;

import java.util.ArrayList;
import java.util.List;

public class userProfil {
    boolean setupIsFinish;
    String email;
    String password;
    List<minecraftUserItem> minecraftUserItems;
    public userProfil(boolean setupIsFinish, String email, String password, List<minecraftUserItem> minecraftUserItems) {
        this.setupIsFinish = setupIsFinish;
        this.email = email;
        this.password = password;
        this.minecraftUserItems = minecraftUserItems;
    }

    public static class minecraftUserItem {
        String email;
        public String username;
        String token;
        String uuid;
        boolean isPremium;

        public minecraftUserItem(String email, String username, String token, String uuid, boolean isPremium) {
             this.email = email;
             this.username = username;
             this.token = token;
             this.uuid = uuid;
             this.isPremium = isPremium;

        }
    }

}
