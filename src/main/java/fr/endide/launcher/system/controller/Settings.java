package fr.endide.launcher.system.controller;

import fr.endide.launcher.system.pageManager;
import fr.endide.launcher.system.saveManager;
import fr.endide.launcher.system.utils.userProfil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;

public class Settings {
    @FXML
    private Button accountAddButton;

    @FXML
    private Button accountDelButton;

    @FXML
    private ListView<userProfil.minecraftUserItem> accountList;

    @FXML
    private Button delThemeButton;

    @FXML
    private Button downloadThemeButton;

    @FXML
    private Button endideAccount;

    @FXML
    private Button shareThemeButton;

    @FXML
    private CheckBox startupPassword;

    @FXML
    private ListView<?> themesDownloaded;

    @FXML
    private ListView<?> themesList;

    @FXML
    void onAddButton(ActionEvent event) {
        pageManager.mcLoginManagerPage();

    }

    @FXML
    void onClickDownloadTheme(ActionEvent event) {

    }

    @FXML
    void onClickEndideAccount(ActionEvent event) {

    }

    @FXML
    void onClickShareTheme(ActionEvent event) {

    }

    @FXML
    void onDelButton(ActionEvent event) {

    }

    @FXML
    void onDelTheme(ActionEvent event) {

    }

    @FXML
    void onStartupPassword(ActionEvent event) {
        if(startupPassword.isSelected()){
            saveManager.changeSettings(true);
        }else{
            saveManager.changeSettings(false);
        }
    }
    public void initialize(){
        ObservableList<userProfil.minecraftUserItem> userItems = FXCollections.observableArrayList(saveManager.minecraftItems);
        accountList.setItems(userItems);
        startupPassword.setSelected(saveManager.startupPassword);
        accountList.refresh();
    }
}
