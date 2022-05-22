package ArtShoppingApplication.controllers;

import ArtShoppingApplication.exceptions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import ArtShoppingApplication.services.UserService;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class AddItemController {
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private TextField category;
    @FXML
    private TextField dimensions;
    @FXML
    private TextField materials;
    @FXML
    private TextField colors;
    @FXML
    private TextField weight;
    @FXML
    private ImageView picture;
    Image fxImage;
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

    public void handleAddItem() {
    }

    public void handleCancel() {
    }

}
