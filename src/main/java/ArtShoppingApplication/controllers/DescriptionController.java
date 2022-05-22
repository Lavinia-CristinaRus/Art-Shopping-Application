package ArtShoppingApplication.controllers;

import ArtShoppingApplication.exceptions.ItemDoesNotExist;
import ArtShoppingApplication.exceptions.UserDoesNotExist;
import ArtShoppingApplication.model.Item;
import ArtShoppingApplication.services.ItemService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static ArtShoppingApplication.services.ItemService.getItemByName;

public class DescriptionController {
    @FXML
    private Text name;
    @FXML
    private Text description;
    @FXML
    private ImageView itemImage;

    @FXML
    public void initialize() throws ItemDoesNotExist, UserDoesNotExist, FileNotFoundException {
        FileInputStream fileIn = new FileInputStream("iname.txt");
        Scanner scan = new Scanner(fileIn);
        String sname = scan.next();
        Item item = getItemByName(sname);
        String desc = item.toString();
        description.setText(desc);
        name.setText(sname);
        itemImage.setImage(new Image(item.getPicture()));
    }

    @FXML
    public void close(ActionEvent event) {
        Stage stage = (Stage) description.getScene().getWindow();
        stage.close();
    }

    public void handleDeleteItem() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/deleteItem.fxml"));
        Parent p = (Parent)fxmlLoader.load();
        Scene scene0 = new Scene(p, 400, 300);
        Stage window = new Stage();
        window.setTitle("Delete Item Page");
        window.setScene(scene0);
        window.show();
    }

    public void handleEditItem() {
        Stage stage = (Stage) description.getScene().getWindow();
        stage.close();
    }

}
