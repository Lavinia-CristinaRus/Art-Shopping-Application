package ArtShoppingApplication.services;

import ArtShoppingApplication.exceptions.*;
import ArtShoppingApplication.model.Item;
import ArtShoppingApplication.model.Request;
import ArtShoppingApplication.model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteBuilder;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.objects.ObjectRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static ArtShoppingApplication.services.FileSystemService.getPathToFile;
import static org.dizitart.no2.objects.filters.ObjectFilters.*;


public class RequestService {
    private static ObjectRepository<Request> requestRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("request-items.db").toFile())
                .openOrCreate("test", "test");
        requestRepository = database.getRepository(Request.class);
    }

    public static void addItem(String name, String description) throws FileNotFoundException {
        FileInputStream fileIn = new FileInputStream("log.txt");
        Scanner scan = new Scanner(fileIn);
        String fbuyer = scan.next();
        boolean ok=true;
        for (Request request : requestRepository.find()) {
            if (Objects.equals(name, request.getName()) && Objects.equals(description, request.getDescription())) ok=false;}
         if(ok==true)    requestRepository.insert(new Request(name, description,0,fbuyer));
    }

    public static List<Request> getMyRequest() throws FileNotFoundException {
        List<Request> myRequests = new ArrayList<>();
        FileInputStream fileIn = new FileInputStream("log.txt");
        Scanner scan = new Scanner(fileIn);
        String fartist = scan.next();
        for (Request request : requestRepository.find()) {
            if(request.getDescription().toLowerCase().contains(fartist.toLowerCase()));
                 myRequests.add(request);
        }
        return myRequests;
    }

    public static void deleteItem() throws FileNotFoundException, UserDoesNotExist {
        FileInputStream fileIn = new FileInputStream("request.txt");
        Scanner scan = new Scanner(fileIn);
        String fname = scan.next();
        requestRepository.remove(eq("name", fname));

    }



    public static void updateRequest(String name, String description, int ok) throws  UserDoesNotExist, FileNotFoundException {
        deleteItem();
        FileInputStream fileIn = new FileInputStream("log.txt");
        Scanner scan = new Scanner(fileIn);
        String fbuyer = scan.next();
        requestRepository.insert(new Request(name,description,ok,fbuyer));
    }

}