package ArtShoppingApplication.controllers;

import ArtShoppingApplication.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class MyItemsController {
    @FXML
    private ListView items;

    @FXML
    public void openItemRegistrationForm(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addItem.fxml"));
        Parent p = (Parent)fxmlLoader.load();
        Scene scene0 = new Scene(p, 600, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Add items page");
        window.setScene(scene0);
        window.show();
    }

    public void toRequests() {

    }

    public void toBuyerList() {

    }

    public void toMyRequests() {

    }

    public void toSignOut() throws IOException {
        new FileWriter("log.txt", false).close();
        Parent p = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Scene scene0 = new Scene(p, 1000, 600);
        Stage window = (Stage) items.getScene().getWindow();
        window.setTitle("Login page");
        window.setScene(scene0);
        window.show();
    }

    public void testDescription(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/description.fxml"));
        Parent p = (Parent)fxmlLoader.load();
        Scene scene0 = new Scene(p, 550, 400);
        Stage window = new Stage();
        window.setTitle("Description Page");
        window.setScene(scene0);
        window.show();
    }

}
