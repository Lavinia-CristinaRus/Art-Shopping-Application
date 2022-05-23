package ArtShoppingApplication.controllers;

import ArtShoppingApplication.exceptions.UserDoesNotExist;
import ArtShoppingApplication.services.ItemService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class AllItemsController {
    private ObservableList<String> itemlist = FXCollections.observableArrayList();

    @FXML
    private ListView items;
    @FXML
    private Text message;

    @FXML
    public void toMyOrders() {

    }



    @FXML
    public void initialize() throws UserDoesNotExist, FileNotFoundException {

        AtomicReference<String> p = new AtomicReference<>("");
        ItemService.getItems().forEach(item -> {
            p.set(item.getName());
            itemlist.add(String.valueOf(p));
        });
        items.getItems().addAll(itemlist);


    }

    public void toSignOut() throws IOException {
        new FileWriter("log.txt", false).close();
        Parent p = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Scene scene0 = new Scene(p, 1000, 600);
        Stage window = (Stage) items.getScene().getWindow();
        window.setTitle("Login page");
        window.setScene(scene0);
        window.show();
    }

    public void toDescriptionSelected(MouseEvent mouseEvent) throws IOException {
        String item = (String)items.getSelectionModel().getSelectedItem();
        if(item==null||item.isEmpty()) message.setText("Nothing was selected.");
        String file = "iname.txt";
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(item);
        fileWriter.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/descriptionBuyer.fxml"));
        Parent p = (Parent)fxmlLoader.load();
        Scene scene0 = new Scene(p, 550, 400);
        Stage window = new Stage();
        window.setTitle("Description");
        window.setScene(scene0);
        window.show();
    }

    public void toAllItems() throws IOException {
        Parent modifyWindow = FXMLLoader.load(getClass().getResource("/allItems.fxml"));
        Scene modifyScene = new Scene(modifyWindow,600,600);
        Stage window = new Stage();
        window.setScene(modifyScene);
        window.show();
        Stage stage = (Stage) message.getScene().getWindow();
        stage.close();
    }

    public void openItemRegistrationForm(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addItem.fxml"));
        Parent p = (Parent)fxmlLoader.load();
        Scene scene0 = new Scene(p, 600, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Add items page");
        window.setScene(scene0);
        window.show();
    }


    public void tomyItems(ActionEvent event) throws IOException {
        Parent modifyWindow = FXMLLoader.load(getClass().getResource("/myItems.fxml"));
        Scene modifyScene = new Scene(modifyWindow,600,600);
        Stage window = new Stage();
        window.setScene(modifyScene);
        window.show();
        Stage stage = (Stage)message.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void toMyRequests(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/myRequests.fxml"));
        Scene scene0 = new Scene(p, 1000, 600);
        Stage window = (Stage) items.getScene().getWindow();
        window.setTitle("Requests page");
        window.setScene(scene0);
        window.show();
    }
}
