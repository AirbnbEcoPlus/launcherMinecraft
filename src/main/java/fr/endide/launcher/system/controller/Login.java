package fr.endide.launcher.system.controller;

import fr.endide.launcher.system.pageManager;
import fr.endide.launcher.system.saveManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
            System.out.println("connected");
            try {
                pageManager.startPage();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
    public void initialize(){
        usernameField.setText(saveManager.email);
    }

}
