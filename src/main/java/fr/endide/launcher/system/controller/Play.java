package fr.endide.launcher.system.controller;

import fr.endide.launcher.system.MainLaunch;
import fr.endide.launcher.system.saveManager;
import fr.endide.launcher.system.updater;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

import java.io.IOException;

public class Play {
    @FXML
    private MenuButton accountsButton;

    @FXML
    private Button playButton;
    @FXML
    private WebView webView;
    @FXML
    private MenuButton versionsButton;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Text statusText;

    public void initialize(){

        accountsButton.getItems().clear();
        statusText.setDisable(true);
        progressBar.setDisable(true);
        for(int index = 0; index < saveManager.getAllAccount().size(); index++){
            MenuItem accountsItem = new MenuItem(saveManager.getAllAccount().get(index).username);
            accountsItem.setOnAction(e -> {
                accountsButton.setText(accountsItem.getText());
            });
            accountsButton.getItems().add(accountsItem);



        }
        versionsButton.getItems().clear();
        for(int index = 0; index < updater.versionsItems.size(); index++){
            MenuItem versionsItem = new MenuItem(updater.versionsItems.get(index).id);
            versionsItem.setOnAction(e -> {
                versionsButton.setText(versionsItem.getText());
            });
                versionsButton.getItems().add(versionsItem);
        }
    }

    @FXML
    void onClickPlayButton(ActionEvent event) {

        statusText.setText("Téléchargement de Minecraft..");
        try {
            updater.downloadMinecraft(versionsButton.getText(), progressBar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
