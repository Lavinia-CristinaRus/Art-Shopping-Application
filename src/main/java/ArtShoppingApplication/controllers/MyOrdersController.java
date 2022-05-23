package ArtShoppingApplication.controllers;

import ArtShoppingApplication.exceptions.UserDoesNotExist;
import ArtShoppingApplication.services.RequestService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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


public class MyOrdersController {

    private ObservableList<String> requestlist = FXCollections.observableArrayList();

    @FXML
    private ListView requests;
    @FXML
    private Text message;



    public void toBuyerList(ActionEvent event) throws IOException{
        Parent p = FXMLLoader.load(getClass().getResource("/buyerPage.fxml"));
        Scene scene0 = new Scene(p, 1000, 600);
        Stage window = (Stage) requests.getScene().getWindow();
        window.setTitle("Items page");
        window.setScene(scene0);
        window.show();
    }

    @FXML
    public void toMyOrders(ActionEvent actionEvent) {
    }

    @FXML
    public void initialize() throws UserDoesNotExist, FileNotFoundException {

        AtomicReference<String> p = new AtomicReference<>("");
        RequestService.getMyOrder().forEach(request -> {
            p.set(request.getName());
            requestlist.add(String.valueOf(p));
        });
        requests.getItems().addAll(requestlist);


    }

    public void toSignOut() throws IOException {
        new FileWriter("log.txt", false).close();
        Parent p = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Scene scene0 = new Scene(p, 1000, 600);
        Stage window = (Stage) requests.getScene().getWindow();
        window.setTitle("Login page");
        window.setScene(scene0);
        window.show();
    }

    public void toDescriptionSelected(MouseEvent mouseEvent) throws IOException {
        String item = (String)requests.getSelectionModel().getSelectedItem();
        if(item==null||item.isEmpty()) message.setText("Nothing was selected.");
        String file = "iname.txt";
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(item);
        fileWriter.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/descriptionOrder.fxml"));
        Parent p = (Parent)fxmlLoader.load();
        Scene scene0 = new Scene(p, 550, 400);
        Stage window = new Stage();
        window.setTitle("Description");
        window.setScene(scene0);
        window.show();
    }

}
