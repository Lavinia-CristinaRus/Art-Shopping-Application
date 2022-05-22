package ArtShoppingApplication.services;

import ArtShoppingApplication.exceptions.*;
import ArtShoppingApplication.model.Item;
import ArtShoppingApplication.model.Request;
import ArtShoppingApplication.model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static ArtShoppingApplication.services.FileSystemService.getPathToFile;


public class RequestService {
    private static ObjectRepository<Request> requestRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("request-items.db").toFile())
                .openOrCreate("test", "test");
        requestRepository = database.getRepository(Request.class);
    }

    public static void addItem(String name, String description) {

    boolean ok=true;
        for (Request request : requestRepository.find()) {
            if (Objects.equals(name, request.getName()) && Objects.equals(description, request.getDescription())) ok=false;}
         if(ok==true)    requestRepository.insert(new Request(name, description));
    }

}