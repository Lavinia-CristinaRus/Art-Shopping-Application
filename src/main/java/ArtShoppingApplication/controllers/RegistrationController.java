package ArtShoppingApplication.controllers;

import ArtShoppingApplication.exceptions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ArtShoppingApplication.services.UserService;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailField;
    @FXML
    private ChoiceBox role;


    @FXML
    public void initialize() {
        role.getItems().addAll("Buyer", "Seller");
    }

    @FXML
    public void handleRegisterAction(ActionEvent event) throws IOException, InterruptedException {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), fullNameField.getText(), addressField.getText(), emailField.getText(), (String) role.getValue());
            registrationMessage.setText("Account created successfully!");
        } catch (EmailAlreadyRegistered | PasswordFieldEmptyException | UsernameFieldEmptyException | FullNameFieldEmptyException | AddressFieldEmptyException | EmailFieldEmptyException | RoleFieldEmptyException e) {
            registrationMessage.setText(e.getMessage());
        }


        if (((String) role.getValue()).equals("Seller")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sellercode.fxml"));
            Parent p = (Parent)fxmlLoader.load();
            SellerCheckController controller = fxmlLoader.<SellerCheckController>getController();
            controller.setEmail(emailField.getText());
            Scene scene0 = new Scene(p, 1000, 600);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Seller register page");
            window.setScene(scene0);
            window.show();
        }

    }

    public void onToLogin(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Scene scene0 = new Scene(p, 1000, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Login page");
        window.setScene(scene0);
        window.show();
    }
}
