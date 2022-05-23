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
        FileInputStream fileIn = new FileInputStream("log.txt");
        Scanner scan = new Scanner(fileIn);
        String fname = scan.next();
        FileInputStream fileIn2 = new FileInputStream("request.txt");
        Scanner scan2 = new Scanner(fileIn2);
        String fname2 = scan2.next();
        requestRepository.remove(and(eq("name", fname2),eq("buyer",fname)));

    }

    public static boolean checkReqExists(String name, String description) throws FileNotFoundException {
        FileInputStream fileIn = new FileInputStream("log.txt");
        Scanner scan = new Scanner(fileIn);
        String fbuyer = scan.next();
        for(Request req : requestRepository.find(and(eq("name", name),eq("description",description)))){
            if(fbuyer.equals(req.getBuyer())) {
                return true;
            }
        }
        return false;
    }

    public static Request getReqByName(String name) throws FileNotFoundException {
        FileInputStream fileIn = new FileInputStream("log.txt");
        Scanner scan = new Scanner(fileIn);
        String fbuyer = scan.next();
        for(Request req : requestRepository.find(and(eq("name", name),eq("buyer",fbuyer)))){
            if(fbuyer.equals(req.getBuyer())) {
                return req;
            }
        }
        return null;
    }


    public static void updateRequest(String name, String description, int ok) throws  UserDoesNotExist, FileNotFoundException {
        deleteItem();
        if(ok==1) {
            for(Request req : requestRepository.find(and(eq("name", name),eq("description",description)))){
                req.setStatus(2);
            }
        }
        FileInputStream fileIn = new FileInputStream("log.txt");
        Scanner scan = new Scanner(fileIn);
        String fbuyer = scan.next();
        requestRepository.insert(new Request(name,description,ok,fbuyer));
    }

}