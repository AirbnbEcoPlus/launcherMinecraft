package fr.endide.launcher.system.controller;

import fr.endide.launcher.system.saveManager;
import fr.endide.launcher.system.MainLaunch;
import fr.endide.launcher.system.pageManager;
import fr.endide.launcher.server.serverManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.MalformedURLException;

import static fr.endide.launcher.server.serverManager.launchServer;

public class Dev {
    public String serverName;
    @FXML
    private TextArea consoleArea;
    @FXML
    private Button createButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ListView<String> listServers;

    @FXML
    private TextField consoleField;

    @FXML
    private Button killButton;

    @FXML
    private Button sendButton;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private TreeView<?> treeView;

    public void initialize(){

        stopButton.setDisable(true);
        startButton.setDisable(true);
        killButton.setDisable(true);
        sendButton.setDisable(true);
        consoleField.setDisable(true);
        consoleArea.setDisable(true);
        listServers.getItems().clear();
        serverManager.loadServers();
        for(int index = 0; index < saveManager.serverList.size(); index++){
            listServers.getItems().add(saveManager.serverList.get(index).name);
        }
        listServers.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            serverName = newVal;
            serverManager.loadServerDashboard(serverName, consoleArea, treeView, startButton, stopButton, killButton, sendButton);
        });

    }

    @FXML
    void onCreate(ActionEvent event) {
        if(MainLaunch.onInternet == true) {
            try {
                pageManager.devCreator();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Vous ne pouvez pas créer de nouveau serveurs sans Internet");
            errorAlert.show();
        }
    }
    @FXML
    void onDelete(ActionEvent event) {

    }

        @FXML
        void onKillProc(ActionEvent event) {

        }

        @FXML
        void onStart(ActionEvent event) {
            try {
                serverManager.launchServer(serverName);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        @FXML
        void onStopProc(ActionEvent event) {

        }



}
