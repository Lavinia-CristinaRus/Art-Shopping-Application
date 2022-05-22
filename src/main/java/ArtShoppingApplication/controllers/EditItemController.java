package ArtShoppingApplication.controllers;

import ArtShoppingApplication.exceptions.*;
import ArtShoppingApplication.services.ItemService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class EditItemController {
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private ChoiceBox category;
    @FXML
    private TextField dimensions;
    @FXML
    private TextField materials;
    @FXML
    private TextField colors;
    @FXML
    private TextField weight;
    @FXML
    private Text message;
    @FXML
    private ImageView picture;
    Image fxImage;
    @FXML
    public void initialize() {
        category.getItems().addAll("Paintings", "Sculptures","Others");
    }


    @FXML
    public void handleSelectPicture() {

        Stage stage = (Stage) picture.getScene().getWindow();

        FileChooser file = new FileChooser();
        file.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File selected = file.showOpenDialog(stage);


        fxImage = new Image(new File(selected.getAbsolutePath()).toURI().toString());
        picture.setImage(fxImage);
    }

    public void handleEditItem() throws NoPictureSelectedException {
        
    }

    public void handleCancel() throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/myItems.fxml"));
        Scene scene2 = new Scene(p, 1500, 800);
        Stage window = (Stage)picture.getScene().getWindow();
        window.setTitle("These are your products");
        window.setScene(scene2);
        window.show();
    }

}
