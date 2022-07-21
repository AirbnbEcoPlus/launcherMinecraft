package fr.endide.bootstraplaunchermc;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.MalformedURLException;

public class mainClass extends Application {
    update update = new update();
    private fileManager fileManager = new fileManager();
    private double xOffset = 0;
    private double yOffset = 0;
    ProgressBar progressBar = new ProgressBar();
    Text textUpdater = new Text();
    @Override

    public void start(Stage primaryStage) {
        if(!fileManager.createGameDir().exists()){
            fileManager.createGameDir().mkdir();
        }
        if(!fileManager.getThemeDir().exists()){
            fileManager.getThemeDir().mkdir();
        }
        if(!fileManager.getJavaDir().exists()){
            fileManager.getJavaDir().mkdir();
        }
        update.updateLauncher();
        primaryStage.setTitle("EndideUpdater");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Group root = new Group();
        Scene scene = new Scene(root, 400, 100, Color.DARKGRAY);
        Text text = new Text();
        text.setText("Mise a jour du launcher");
        text.setLayoutX(120);
        text.setLayoutY(20);
        progressBar.setLayoutX(0);
        progressBar.setLayoutY(40);
        progressBar.setPrefSize(400, 50);
        root.getChildren().add(text);
        root.getChildren().add(progressBar);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
