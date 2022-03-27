package fr.endide.launcher.system.controller;

import fr.endide.launcher.system.pageManager;
import fr.endide.launcher.system.saveManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class firstSetup {
McAccountManager mcAccountManager = new McAccountManager();
    @FXML
    public Button accept;
    @FXML
    public Button backupAccount;
    @FXML
    public TextField emailField;
    @FXML
    public PasswordField repasswordField;
    @FXML
    public ListView<String> listView;
    @FXML
    public PasswordField passwordField;
    @FXML
    private Button delMcAccount;
    @FXML
    private Button addMcAccount;

    boolean ifNoAccountIsCreated = false;
    public void acceptButton(ActionEvent actionEvent) {

        if (emailField.getText() != "" && passwordField.getText() != "" && repasswordField.getText() != "") {

            if (saveManager.getAllAccount().size() != 0) {
                saveManager.finishSetup(emailField.getText(), passwordField.getText());
            } else {
                System.out.println("Vous devez connecter au moins un compte");
            }
        } else {
            System.out.println("tout les champs ne sont pas rempli");
        }

    }

    @FXML
    void addAccountMc(ActionEvent event)  {
        pageManager.mcLoginManagerPage();

    }

    @FXML
    void delAccountMc(ActionEvent event) {

    }
    public void initialize(){
        listView.getItems().clear();
        for(int index = 0; index < saveManager.getAllAccount().size(); index++){
            listView.getItems().add(saveManager.getAllAccount().get(index).username);

        }
    }

}
