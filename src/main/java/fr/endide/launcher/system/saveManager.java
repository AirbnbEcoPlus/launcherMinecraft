package fr.endide.launcher.system;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class saveManager {
    static fileManager fileManager = new fileManager();
    static Gson gson = createGsonInstance();
    public static boolean setupIsFinish;
    public static String email;
    public static String password;
    public static List<userProfil.minecraftUserItem> minecraftItems = new ArrayList<>();

    public static Gson createGsonInstance(){
        return  new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
    }


    public static void saveUser(String email, String username, String token, String uuid, boolean isPremium){
        userProfil.minecraftUserItem userItem = new userProfil.minecraftUserItem(email, username, token , uuid, isPremium);
        minecraftItems.add(userItem);
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileManager.createGameDir() + File.separator + "launcherConfig.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        userProfil profile = new userProfil(false, "null", "null", minecraftItems);
        gson.toJson(profile, writer);
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<userProfil.minecraftUserItem> getAllAccount(){
        return minecraftItems;
    }
    public static void getConfigData(){
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(fileManager.createGameDir() + File.separator + "launcherConfig.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        userProfil users = gson.fromJson(reader, userProfil.class);
        setupIsFinish = users.setupIsFinish;
        email = users.email;
        password = users.password;
        for(int index = 0; index < users.minecraftUserItems.size(); index++){
            minecraftItems.add(users.minecraftUserItems.get(index));
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getOneAccount(String username){

    }
    public static void finishSetup(String email, String password){
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileManager.createGameDir() + File.separator + "launcherConfig.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        userProfil profile = new userProfil(true, email, password, minecraftItems);
        gson.toJson(profile, writer);
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(String username){

    }
}


