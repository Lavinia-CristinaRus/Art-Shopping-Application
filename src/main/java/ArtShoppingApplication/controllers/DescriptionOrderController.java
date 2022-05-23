package ArtShoppingApplication.controllers;

import ArtShoppingApplication.exceptions.ItemDoesNotExist;
import ArtShoppingApplication.exceptions.UserDoesNotExist;
import ArtShoppingApplication.model.Item;
import ArtShoppingApplication.model.Request;
import ArtShoppingApplication.services.RequestService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ArtShoppingApplication.services.ItemService.getItem;
import static ArtShoppingApplication.services.ItemService.getItemByName;
import static ArtShoppingApplication.services.RequestService.*;

public class DescriptionOrderController {
    @FXML
    private Text name;
    @FXML
    private Text description;
    @FXML
    private ImageView itemImage;


    int ok;

    @FXML
    public void initialize() throws ItemDoesNotExist, FileNotFoundException, UserDoesNotExist {
        FileInputStream fileIn = new FileInputStream("iname.txt");
        Scanner scan = new Scanner(fileIn);
        String sname = scan.next();
        Item item = getItem(sname);
        Request req = getRequest();
        ok=req.getStatus();
        String desc = item.toString();
        if(ok==0)
           description.setText(desc+"\nYour order status is IN PENDING");
          else  if(ok==1)
            description.setText(desc+"\nYour order status is ACCEPTED");
            else if(ok==2)
               description.setText(desc+"\nYour order status is DENIED");

        name.setText(sname);
        itemImage.setImage(new Image(item.getPicture()));
    }

    @FXML
    public void close(ActionEvent event) throws IOException {
        Stage stage = (Stage) description.getScene().getWindow();
        stage.close();

    }

    public void handleRetractOrder() throws IOException, UserDoesNotExist {
        if(ok==0)   RequestService.deleteItem();
    }


}
