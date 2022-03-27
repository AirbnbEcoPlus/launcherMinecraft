package fr.endide.launcher.system;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;


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



public class MainLaunch extends Application {
	updater updater = new updater();
	private fileManager fileManager = new fileManager();
	private Stage primaryStage;
    private BorderPane rootLayout;
	private double xOffset = 0;
	private double yOffset = 0;
	@Override

	public void start(Stage stage) {
		if(new File(fileManager.createGameDir() + File.separator + "launcherConfig.json").exists()){
			saveManager.getConfigData();
		}

		this.primaryStage = stage;
        this.primaryStage.setTitle("Endide");
        this.primaryStage.getIcons().add(new Image("img/logo.png"));
        this.primaryStage.initStyle(StageStyle.UNDECORATED);
		if(saveManager.setupIsFinish != true) {
			try {
				initSetupLayout();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				initSetupLayout();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		rootLayout.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				xOffset = mouseEvent.getSceneX();
				yOffset = mouseEvent.getSceneY();
			}
		});
		rootLayout.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				stage.setX(mouseEvent.getScreenX() - xOffset);
				stage.setY(mouseEvent.getScreenY() - yOffset);
			}
		});

		if(saveManager.setupIsFinish != true) {
			setupPage();
		}else{
			loginPage();
		}
	}
	public void initSetupLayout() throws MalformedURLException {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fileManager.getThemes("borderPaneSetup",fileManager.getThemeName()));
        try {
		rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
        rootLayout.styleProperty().set("-fx-background-color:#515151;");
        Scene scene = new Scene(rootLayout);
        
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	public void setupPage() {
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fileManager.getThemes("firstSetup",fileManager.getThemeName()));
		AnchorPane setupPage = (AnchorPane) loader.load();
		
        rootLayout.setCenter(setupPage);
		 } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

	public void loginPage() {
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fileManager.getThemes("login",fileManager.getThemeName()));
		AnchorPane loginPage = (AnchorPane) loader.load();
		
        rootLayout.setCenter(loginPage);
		 } catch (IOException e) {
	            e.printStackTrace();
		}
	}
	public Stage getPrimaryStage() {
		return primaryStage;
	}
}
