package ArtShoppingApplication.controllers;

import ArtShoppingApplication.exceptions.*;
import ArtShoppingApplication.model.Item;
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

import static ArtShoppingApplication.services.ItemService.deleteItem;
import static ArtShoppingApplication.services.ItemService.getItemByName;

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
    public void initialize() throws ItemDoesNotExist, FileNotFoundException {
        FileInputStream fileIn = new FileInputStream("iname.txt");
        Scanner scan = new Scanner(fileIn);
        String fname = scan.next();
        category.getItems().addAll("Paintings", "Sculptures","Others");
        Item i = getItemByName(fname);
        name.setText(fname);
        description.setText(i.getDescription());
        price.setText(Integer.toString(i.getPrice()));
        category.setValue(i.getCategory());
        dimensions.setText(i.getDimensions());
        materials.setText(i.getMaterial());
        colors.setText(i.getColors());
        weight.setText(Integer.toString(i.getWeight()));
        picture.setImage(new Image(i.getPicture()));
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

    public void handleSaveItem() throws NoPictureSelectedException {
        try {
            FileInputStream fileIn = new FileInputStream("log.txt");
            Scanner scan = new Scanner(fileIn);
            String artist = scan.next();
            if(fxImage==null) {
                throw new NoPictureSelectedException();
            }
            ItemService.updateItem(name.getText(), fxImage.getUrl(), description.getText(), price.getText(), (String)category.getValue(), dimensions.getText(), materials.getText(), colors.getText(), weight.getText(), artist);
            message.setText("Item saved successfully!");
        }
        catch (NameAlreadyUsedException | NameFieldEmptyException | NoPictureSelectedException | DescriptionFieldEmptyException | PriceFieldEmptyException | PriceNotValidException | CategoryNotSelectedException | DimensionsFieldEmptyException | WeightFieldEmptyException | WeightNotValidException | FileNotFoundException | UserDoesNotExist ex) {
            message.setText(ex.getMessage());

        }

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
