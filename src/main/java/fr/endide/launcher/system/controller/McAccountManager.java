package fr.endide.launcher.system.controller;

import fr.endide.launcher.system.fileManager;
import fr.endide.launcher.system.saveManager;
import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.model.AuthAgent;
import fr.litarvan.openauth.model.response.AuthResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

public class McAccountManager {
    fileManager fileManager = new fileManager();
    String authentificator = null;
    @FXML
    private Button crackAccount;

    @FXML
    private Button microsoftAccount;

    @FXML
    private Button mojangAccount;
    @FXML
    private Text emailText;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Text passwordText;

    @FXML
    private TextField textField;
    @FXML
    private Button okButton;
    @FXML
    private Text usernameText;
    @FXML
    void addCrack(ActionEvent event) {
        usernameText.setOpacity(1);
        emailText.setOpacity(0);
        passwordText.setOpacity(0);
        textField.setDisable(false);
        passwordField.setDisable(true);
        authentificator = "crack";
    }

    @FXML
    void addMicrosoft(ActionEvent event) {
        usernameText.setOpacity(0);
        emailText.setOpacity(1);
        passwordText.setOpacity(1);
        textField.setDisable(false);
        passwordField.setDisable(false);
        authentificator = "microsoft";
    }

    @FXML
    void addMojang(ActionEvent event) {
        usernameText.setOpacity(0);
        emailText.setOpacity(1);
        passwordText.setOpacity(1);
        textField.setDisable(false);
        passwordField.setDisable(false);
        authentificator = "mojang";
    }
    @FXML
    void acceptButton(ActionEvent event) throws MicrosoftAuthenticationException, AuthenticationException {
        String email = textField.getText();
        String password = passwordField.getText();
        if(authentificator == "microsoft") {
            MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
            MicrosoftAuthResult result = authenticator.loginWithCredentials(email, password);
            saveManager.saveUser(usernameText.getText(), result.getProfile().getName(), result.getAccessToken(), result.getProfile().getId(), true);
            Node source = (Node)  event.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();

        }

        if(authentificator == "mojang") {
            Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);
            AuthResponse authResponse = authenticator.authenticate(AuthAgent.MINECRAFT, email, password, "");
            saveManager.saveUser(usernameText.getText(),authResponse.getSelectedProfile().getName(), authResponse.getAccessToken(), authResponse.getSelectedProfile().getId(), true);
                Node source = (Node)  event.getSource();
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
        }
        if(authentificator =="crack"){
            saveManager.saveUser("none", email, "none", UUID.randomUUID().toString(), false);
                Node source = (Node)  event.getSource();
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
        }
    }



}
