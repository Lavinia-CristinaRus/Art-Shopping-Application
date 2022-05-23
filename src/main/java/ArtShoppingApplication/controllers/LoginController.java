package ArtShoppingApplication.controllers;

import ArtShoppingApplication.exceptions.WrongPasswordException;
import ArtShoppingApplication.model.User;
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


public class LoginController {


    @FXML
    private PasswordField PasswordField;
    @FXML
    private TextField EmailField;
    @FXML
    private Label login_test;
    @FXML
    private Text loginMessage;


    public void login() throws Exception, WrongPasswordException {
        User user = UserService.verify(EmailField.getText(), PasswordField.getText());
        loginMessage.setText("successful login!");
        String file = "log.txt";
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(EmailField.getText());
        fileWriter.close();
        if (user.getRole().equals("Seller")) {
            Parent modifyWindow = FXMLLoader.load(getClass().getResource("/myItems.fxml"));
            Scene modifyScene = new Scene(modifyWindow,600,600);
            Stage window = new Stage();
            window.setScene(modifyScene);
            window.show();
            Stage stage = (Stage) EmailField.getScene().getWindow();
            stage.close();
        }
        else {
            Parent modifyWindow = FXMLLoader.load(getClass().getResource("/buyerPage.fxml"));
            Scene modifyScene = new Scene(modifyWindow,600,600);
            Stage window = new Stage();
            window.setScene(modifyScene);
            window.show();
            Stage stage = (Stage) EmailField.getScene().getWindow();
            stage.close();
        }
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

