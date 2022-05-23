package ArtShoppingApplication.controllers;

import ArtShoppingApplication.exceptions.ItemDoesNotExist;
import ArtShoppingApplication.exceptions.UserDoesNotExist;
import ArtShoppingApplication.model.Item;
import ArtShoppingApplication.model.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static ArtShoppingApplication.services.ItemService.getItemByName;
import static ArtShoppingApplication.services.RequestService.checkReqExists;
import static ArtShoppingApplication.services.RequestService.getReqByName;

import ArtShoppingApplication.services.RequestService;
public class DescriptionRequestController {
    @FXML
    private Text name;
    @FXML
    private Text description;
    @FXML
    private ImageView itemImage;
    @FXML
    private Button deny;
    @FXML
    private Button accept;

    @FXML
    public void initialize() throws ItemDoesNotExist, FileNotFoundException {
        FileInputStream fileIn = new FileInputStream("iname.txt");
        Scanner scan = new Scanner(fileIn);
        String sname = scan.next();
        Request request = getReqByName(sname);
        if(request.getStatus()==0){
            Item item = getItemByName(sname);
            String desc = request.getDescription();
            description.setText(desc);
            name.setText(sname);
            itemImage.setImage(new Image(item.getPicture()));
        }
        else {
            description.setText(request.getDescription());
            name.setText(sname);
            accept.setDisable(true);
            deny.setDisable(true);
        }
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
