package fr.endide.launcher.system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.MalformedURLException;

public class pageManager {


    private static AnchorPane mcLoginManager;
    public static void mcLoginManagerPage() {
        Stage stage = new Stage();
        stage.setTitle("Endide");
        stage.getIcons().add(new Image("img/logo.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(fileManager.getThemes("mcAccountManager",fileManager.getThemeName()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            mcLoginManager = (AnchorPane) loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Scene scene = new Scene(mcLoginManager);
        stage.setScene(scene);
        stage.show();
    }
    private static BorderPane rootLayout2;
    public static void startPage() throws MalformedURLException {
        Stage stage = new Stage();
        stage.setTitle("Endide");
        stage.getIcons().add(new Image("img/logo.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fileManager.getThemes("borderPane",fileManager.getThemeName()));
        try {
            rootLayout2 = (BorderPane) loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        rootLayout2.styleProperty().set("-fx-background-color:#515151;");
        Scene scene = new Scene(rootLayout2);
        stage.setScene(scene);
        stage.show();
        setPlayPage();
    }
    public static void setPlayPage(){
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(fileManager.getThemes("play",fileManager.getThemeName()));
            AnchorPane playPage = (AnchorPane) loader.load();
            rootLayout2.setCenter(playPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setModsPage(){
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(fileManager.getThemes("mods",fileManager.getThemeName()));
            AnchorPane modsPage = loader.load();
            rootLayout2.setCenter(modsPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setDevPage(){
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(fileManager.getThemes("dev",fileManager.getThemeName()));
            AnchorPane devPage = loader.load();
            rootLayout2.setCenter(devPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setSettingsPage(){
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(fileManager.getThemes("settings",fileManager.getThemeName()));
            AnchorPane settingsPage = loader.load();
            rootLayout2.setCenter(settingsPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
