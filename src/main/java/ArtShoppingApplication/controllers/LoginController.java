package ArtShoppingApplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintStream;


public class LoginController {


    @FXML
    private javafx.scene.control.PasswordField PasswordField;
    @FXML
    private TextField EmailField;
    @FXML
    private Label login_test;





    public void onToRegistration(ActionEvent event) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/register.fxml"));
        Scene scene22=new Scene(p,1000,600);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Register page");
        window.setScene(scene22);
        window.show();
    }
}

