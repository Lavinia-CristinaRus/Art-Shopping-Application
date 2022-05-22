package ArtShoppingApplication.controllers;

import ArtShoppingApplication.exceptions.UserDoesNotExist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

import static ArtShoppingApplication.services.ItemService.deleteItem;

public class DeleteItemController {
    @FXML
    private Text confirm;

    @FXML
    public void close() {
        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
    }

    public void handleDeleteItem() throws IOException, UserDoesNotExist {
        deleteItem();
        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
    }

    public void handleCancel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/description.fxml"));
        Parent p = (Parent)fxmlLoader.load();
        Scene scene0 = new Scene(p, 550, 400);
        Stage window = new Stage();
        window.setTitle("Description");
        window.setScene(scene0);
        window.show();
    }
}
