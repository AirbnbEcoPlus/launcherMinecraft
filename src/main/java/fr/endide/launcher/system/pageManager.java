package fr.endide.launcher.system;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.MalformedURLException;

public class pageManager {
    private static double xOffset = 0;
    private static double yOffset = 0;

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
        rootLayout2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
            }
        });
        rootLayout2.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setX(mouseEvent.getScreenX() - xOffset);
                stage.setY(mouseEvent.getScreenY() - yOffset);
            }
        });
    }
    public static void setPlayPage(){
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(fileManager.getThemes("play",fileManager.getThemeName()));
            AnchorPane playPage = loader.load();
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
    static AnchorPane devCreator;
    public static Stage devCreatorStage;
    public static void devCreator() throws MalformedURLException {
        devCreatorStage = new Stage();
        devCreatorStage.setTitle("Endide");
        devCreatorStage.getIcons().add(new Image("img/logo.png"));
        devCreatorStage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fileManager.getThemes("devCreate",fileManager.getThemeName()));
        try {
            devCreator = loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        devCreator.styleProperty().set("-fx-background-color:#515151;");
        Scene scene = new Scene(devCreator);
        devCreatorStage.setScene(scene);

        devCreatorStage.show();
    }

}
