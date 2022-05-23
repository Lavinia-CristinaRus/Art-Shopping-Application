package ArtShoppingApplication.controllers;

import ArtShoppingApplication.exceptions.*;
import ArtShoppingApplication.model.Item;
import ArtShoppingApplication.services.ItemService;
import ArtShoppingApplication.services.RequestService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static ArtShoppingApplication.services.ItemService.getItem;


public class DescriptionBuyerController {
    @FXML
    private Text name;
    @FXML
    private Text description;
    @FXML
    private ImageView itemImage;
    @FXML
    private Button reqbtn;

    @FXML
    public void initialize() throws ItemDoesNotExist, FileNotFoundException {
        FileInputStream fileIn = new FileInputStream("iname.txt");
        Scanner scan = new Scanner(fileIn);
        String sname = scan.next();
        Item item = getItem(sname);
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


    public void handleRequestItem() throws IOException {
        String file = "request.txt";
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(name.getText());
        fileWriter.close();
            RequestService.addItem(name.getText(),description.getText());
    }

}
