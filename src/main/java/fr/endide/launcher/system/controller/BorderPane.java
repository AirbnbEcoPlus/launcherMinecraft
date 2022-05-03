package fr.endide.launcher.system.controller;

import fr.endide.launcher.system.pageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BorderPane {
    @FXML
    private Button devButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button modsButton;

    @FXML
    private Button playButton;

    @FXML
    private Button quitButton;

    @FXML
    private Button settingsButton;

    @FXML
    void dev(ActionEvent event) {
        pageManager.setDevPage();
    }

    @FXML
    void help(ActionEvent event) {

    }

    @FXML
    void mods(ActionEvent event) {
        pageManager.setModsPage();
    }

    @FXML
    void play(ActionEvent event) {
        pageManager.setPlayPage();
    }

    @FXML
    void quit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void settings(ActionEvent event) {
        pageManager.setSettingsPage();
    }
}
