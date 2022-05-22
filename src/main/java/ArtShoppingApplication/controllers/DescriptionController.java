package ArtShoppingApplication.controllers;

import ArtShoppingApplication.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class DescriptionController {
    @FXML
    private Text name;
    @FXML
    private Text description;

    @FXML
    public void close(ActionEvent event) {
        Stage stage = (Stage) description.getScene().getWindow();
        stage.close();
    }

    public void handleDeleteItem() {
        Stage stage = (Stage) description.getScene().getWindow();
        stage.close();
    }

    public void handleEditItem() {
        Stage stage = (Stage) description.getScene().getWindow();
        stage.close();
    }

}
