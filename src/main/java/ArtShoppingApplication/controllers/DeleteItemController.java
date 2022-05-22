package ArtShoppingApplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteItemController {
    @FXML
    private Text confirm;

    @FXML
    public void close(ActionEvent event) {
        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
    }

    public void handleDeleteItem() throws IOException {
        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
    }

    public void handleCancel(ActionEvent event) {
        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
    }
}
