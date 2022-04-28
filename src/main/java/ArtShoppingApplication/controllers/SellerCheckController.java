package ArtShoppingApplication.controllers;

import ArtShoppingApplication.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SellerCheckController {
    @FXML
    private TextField keyField;

    @FXML
    Text ErrorField;

    private String key = "12345";
     String mail="";
     boolean ok=false;


    public  void setEmail(String email) {
         mail = email;
         System.out.println(mail);
    }

    public void verifySellerrAction(ActionEvent actionEvent) {
        if (Objects.equals(key, keyField.getText())) {
            ErrorField.setText("Account created successfully!");
            ok=true;
        } else {
            ErrorField.setText("Incorrect key!\n Try again or go back to the registration page and create a Buyer Account.");
        }
    }

    public void onToRegistration(ActionEvent event) throws IOException {
        if(ok==false) UserService.deleteSeller(mail);
        Parent p= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/register.fxml")));
        Scene scene22=new Scene(p,1000,600);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Register page");
        window.setScene(scene22);
        window.show();
    }

}

