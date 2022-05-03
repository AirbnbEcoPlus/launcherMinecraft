package fr.endide.launcher.system.controller;

import fr.endide.launcher.system.saveManager;
import fr.endide.launcher.system.updater;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class Play {
    @FXML
    private MenuButton accountsButton;

    @FXML
    private Button playButton;

    @FXML
    private MenuButton versionsButton;

    public void initialize(){
        accountsButton.getItems().clear();
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

    }

}
