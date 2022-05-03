package ArtShoppingApplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import ArtShoppingApplication.services.UserService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;


public class LoginController {


    @FXML
    private PasswordField PasswordField;
    @FXML
    private TextField EmailField;
    @FXML
    private Label login_test;
    @FXML
    private Text loginMessage;


    public void login(ActionEvent action) throws Exception {
        if (UserService.verify(EmailField.getText(), PasswordField.getText()) == 1) {
            loginMessage.setText("successful login!");
//            Parent modifyWindow = FXMLLoader.load(getClass().getResource("/seller.fxml"));
//            Scene modifyScene = new Scene(modifyWindow);
//            Stage window = new Stage();
//            window.setScene(modifyScene);
//            window.show();
            Stage stage = (Stage) EmailField.getScene().getWindow();
            stage.close();
        }
        if (UserService.verify(EmailField.getText(), PasswordField.getText()) == 2) {
            loginMessage.setText("successful login!");
//            Parent modifyWindow = FXMLLoader.load(getClass().getResource("/buyer.fxml"));
//            Scene modifyScene = new Scene(modifyWindow);
//            Stage window = new Stage();
//            window.setScene(modifyScene);
//            window.show();
            Stage stage = (Stage) EmailField.getScene().getWindow();
            stage.close();
        }
        if (UserService.verify(EmailField.getText(), PasswordField.getText()) == 0) {
            loginMessage.setText("Incorrect password!");
            PasswordField.clear();
            EmailField.clear();
            return;
        }
        PasswordField.clear();
        EmailField.clear();
        loginMessage.setText("There is no account with that email address!");
    }


    public void onToRegistration(ActionEvent event) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/register.fxml"));
        Scene scene22=new Scene(p,1000,600);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Register page");
        window.setScene(scene22);
        window.show();
    }
}

