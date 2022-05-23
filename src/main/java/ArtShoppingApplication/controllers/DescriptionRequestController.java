package ArtShoppingApplication.controllers;

import ArtShoppingApplication.exceptions.ItemDoesNotExist;
import ArtShoppingApplication.exceptions.UserDoesNotExist;
import ArtShoppingApplication.model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static ArtShoppingApplication.services.ItemService.getItemByName;
import ArtShoppingApplication.services.RequestService;
public class DescriptionRequestController {
    @FXML
    private Text name;
    @FXML
    private Text description;
    @FXML
    private ImageView itemImage;

    @FXML
    public void initialize() throws ItemDoesNotExist, FileNotFoundException {
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

    public void handleAcceptOrder() throws IOException, UserDoesNotExist {
        RequestService.updateRequest(name.getText() ,description.getText(), 1);

    }

    public void handleDenyOrder() throws IOException, UserDoesNotExist {
        RequestService.updateRequest(name.getText() ,description.getText(), 2);
    }

}
