package fr.endide.launcher.system.controller;

import fr.endide.launcher.server.serverManager;
import fr.endide.launcher.system.updater;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class DevCreate {
    serverManager serverManager = new serverManager();
    @FXML
    private MenuButton apiMenu;

    @FXML
    private Button createButton;

    @FXML
    private TextField nameField;

    @FXML
    private MenuButton ramMenu;

    @FXML
    private MenuButton versionsMenu;
    @FXML
    private TextArea consoleArea;
    @FXML
    void onCreate(ActionEvent event) {
        createButton.setDisable(true);
        nameField.setDisable(true);
        ramMenu.setDisable(true);
        versionsMenu.setDisable(true);
        apiMenu.setDisable(true);
        try {
            serverManager.createServer(nameField.getText(), versionsMenu.getText(), ramMenu.getText(), apiMenu.getText(), consoleArea);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void initialize(){
        versionsMenu.getItems().clear();
        for(int index = 0; index < updater.versionsItems.size(); index++){
            MenuItem versionsItem = new MenuItem(updater.versionsItems.get(index).id);
            versionsItem.setOnAction(e -> {
                versionsMenu.setText(versionsItem.getText());
            });
            versionsMenu.getItems().add(versionsItem);
        }
        MenuItem ram1 = new MenuItem("512");
        ram1.setOnAction(e -> {
            ramMenu.setText(ram1.getText());
        });
        MenuItem ram2 = new MenuItem("1024");
        ram2.setOnAction(e -> {
            ramMenu.setText(ram2.getText());
        });
        MenuItem ram3 = new MenuItem("2048");
        ram3.setOnAction(e -> {
            ramMenu.setText(ram3.getText());
        });
        MenuItem ram4 = new MenuItem("4096");
        ram4.setOnAction(e -> {
            ramMenu.setText(ram4.getText());
        });
        ramMenu.getItems().add(ram1);
        ramMenu.getItems().add(ram2);
        ramMenu.getItems().add(ram3);
        ramMenu.getItems().add(ram4);
        ramMenu.getItems().add(new MenuItem("Custom"));

        MenuItem api1 = new MenuItem("bukkit");
        api1.setOnAction(e -> {
            apiMenu.setText(api1.getText());
        });
        MenuItem api2 = new MenuItem("spigot");
        api2.setOnAction(e -> {
            apiMenu.setText(api2.getText());
        });
        apiMenu.getItems().add(api1);
        apiMenu.getItems().add(api2);

    }

}
