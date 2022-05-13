package fr.endide.launcher.system.controller;

import fr.endide.launcher.system.pageManager;
import fr.endide.launcher.system.saveManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import static java.lang.invoke.MethodHandles.loop;

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
                Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                errorAlert.setHeaderText("Configuration terminer vous pouvez relancer le launcher");
                errorAlert.show();
                System.exit(0);
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Vous devez connecter au moins un compte");
                errorAlert.show();
            }
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Tout les champs ne sont pas rempli");
            errorAlert.show();

        }

    }

    @FXML
    void addAccountMc(ActionEvent event)  {
        pageManager.mcLoginManagerPage();
        listView.getItems().clear();
        for (int index = 0; index < saveManager.minecraftItems.size(); index++) {
            listView.getItems().add(saveManager.minecraftItems.get(index).username);
        }
    }

    @FXML
    void delAccountMc(ActionEvent event) {

    }
    public void initialize(){
        listView.getItems().clear();
        for (int index = 0; index < saveManager.minecraftItems.size(); index++) {
            listView.getItems().add(saveManager.minecraftItems.get(index).username);
        }
        


    }

}
