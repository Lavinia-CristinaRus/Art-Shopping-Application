package ArtShoppingApplication.controllers;

import ArtShoppingApplication.exceptions.UserDoesNotExist;
import ArtShoppingApplication.services.ItemService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.concurrent.atomic.AtomicReference;


public class BuyerController {

    private ObservableList<String> itemlist = FXCollections.observableArrayList();

    @FXML
    private ListView items;
    @FXML
    private Text message;
    @FXML
    private TextField searchTerm;
    @FXML


    public void toMyOrders() {

    }

    public void toBuyerList() {

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

    public void search(ActionEvent actionEvent) throws FileNotFoundException {
        ObservableList<String> itemlist2 = FXCollections.observableArrayList();
        String sTerm = searchTerm.getText();
        AtomicReference<String> p = new AtomicReference<>("");
        ItemService.getItemsByName(sTerm).forEach(item -> {
            p.set(item.getName());
            itemlist2.add(String.valueOf(p));
        });
        items.setItems(itemlist2);
    }
}
