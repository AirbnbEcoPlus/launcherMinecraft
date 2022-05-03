package fr.endide.launcher.system.controller;

import fr.endide.launcher.system.pageManager;
import fr.endide.launcher.system.saveManager;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.util.Duration;

public class Settings {
    @FXML
    private Button accountAddButton;

    @FXML
    private Button accountDelButton;

    @FXML
    private ListView<String> accountList;

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
        ScheduledService<Void> refreshList = new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {

                    @Override
                    protected Void call() {
                        accountList.getItems().clear();
                        for (int index = 0; index < saveManager.minecraftItems.size(); index++) {
                            accountList.getItems().add(saveManager.minecraftItems.get(index).username);
                        }
                        return null;
                    }
                };
            }
        };
        refreshList.setPeriod(Duration.seconds(1));
        refreshList.start();
        startupPassword.setSelected(saveManager.startupPassword);

    }
}
