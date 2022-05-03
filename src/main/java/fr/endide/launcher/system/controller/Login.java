package fr.endide.launcher.system.controller;

import fr.endide.launcher.system.MainLaunch;
import fr.endide.launcher.system.pageManager;
import fr.endide.launcher.system.saveManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.MalformedURLException;

public class Login {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void onClick(ActionEvent event) {
        if(usernameField.getText().equals(saveManager.email) && passwordField.getText().equals(saveManager.password)){
            try {
                pageManager.startPage();
                MainLaunch.hideLoginPage();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Mot de passe incorrect");
            errorAlert.showAndWait();
        }
    }
    public void initialize(){
        usernameField.setText(saveManager.email);
    }

}
