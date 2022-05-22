package ArtShoppingApplication.services;

import ArtShoppingApplication.exceptions.*;
import ArtShoppingApplication.model.Item;
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
import static ArtShoppingApplication.services.UserService.searchByEmail;
import static org.dizitart.no2.objects.filters.ObjectFilters.and;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class ItemService {
    private static ObjectRepository<Item> itemRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registered-items.db").toFile())
                .openOrCreate("test", "test");
        itemRepository = database.getRepository(Item.class);
    }

    public static void addItem(String name, String picture, String description, String price, String category, String dimensions, String material, String colors, String weight, String ssartist) throws NameAlreadyUsedException, NameFieldEmptyException, NoPictureSelectedException, DescriptionFieldEmptyException, PriceFieldEmptyException, PriceNotValidException, CategoryNotSelectedException, DimensionsFieldEmptyException, WeightFieldEmptyException, WeightNotValidException, UserDoesNotExist {
        checkNameFieldIsNotEmpty(name);
        checkNameIsUnic(name);
        checkPictureIsSelected(picture);
        checkDescriptionFieldIsNotEmpty(description);
        checkPriceFieldIsNotEmpty(price);
        checkPriceIsAValue(price);
        Integer iprice = Integer.valueOf(price);
        checkCategoryIsSelected(category);
        checkDimensionsFieldIsNotEmpty(dimensions);
        checkWeightFieldIsNotEmpty(weight);
        checkWeightIsAValue(weight);
        Integer iweight = Integer.valueOf(weight);
        itemRepository.insert(new Item(name, picture, description, iprice, category, dimensions, material, colors, iweight, ssartist));
    }

    public static void updateItem(String name, String picture, String description, String price, String category, String dimensions, String material, String colors, String weight, String ssartist) throws NameAlreadyUsedException, NameFieldEmptyException, NoPictureSelectedException, DescriptionFieldEmptyException, PriceFieldEmptyException, PriceNotValidException, CategoryNotSelectedException, DimensionsFieldEmptyException, WeightFieldEmptyException, WeightNotValidException, UserDoesNotExist, FileNotFoundException {
        checkNameFieldIsNotEmpty(name);
        FileInputStream fileIn = new FileInputStream("iname.txt");
        Scanner scan = new Scanner(fileIn);
        String fname = scan.next();
        if(!name.equals(fname)) {checkNameIsUnic(name);}
        checkPictureIsSelected(picture);
        checkDescriptionFieldIsNotEmpty(description);
        checkPriceFieldIsNotEmpty(price);
        checkPriceIsAValue(price);
        Integer iprice = Integer.valueOf(price);
        checkCategoryIsSelected(category);
        checkDimensionsFieldIsNotEmpty(dimensions);
        checkWeightFieldIsNotEmpty(weight);
        checkWeightIsAValue(weight);
        Integer iweight = Integer.valueOf(weight);
        deleteItem();
        itemRepository.insert(new Item(name, picture, description, iprice, category, dimensions, material, colors, iweight, ssartist));
    }

    private static void checkWeightIsAValue(String weight) throws WeightNotValidException{
        if(!weight.matches("\\d+"))  {
            throw new WeightNotValidException();
        }
    }
    private static void checkWeightFieldIsNotEmpty(String weight) throws WeightFieldEmptyException{
        if(weight.equals("")) {
            throw new WeightFieldEmptyException();
        }
    }

    private static void checkDimensionsFieldIsNotEmpty(String dimensions) throws DimensionsFieldEmptyException{
        if(dimensions.equals("")) {
            throw new DimensionsFieldEmptyException();
        }
    }

    private static void checkCategoryIsSelected(String category) throws CategoryNotSelectedException{
        if(category==null) {
            throw new CategoryNotSelectedException();
        }
    }

    private static void checkPriceIsAValue(String price) throws PriceNotValidException{
        if(!price.matches("\\d+"))  {
            throw new PriceNotValidException();
        }
    }
    private static void checkPriceFieldIsNotEmpty(String price) throws PriceFieldEmptyException{
        if(price.equals("")) {
            throw new PriceFieldEmptyException();
        }
    }

    private static void checkDescriptionFieldIsNotEmpty(String description) throws DescriptionFieldEmptyException{
        if(description.equals("")) {
            throw new DescriptionFieldEmptyException();
        }
    }

    private static void checkPictureIsSelected(String picture) throws NoPictureSelectedException{
        if(picture.equals("")) {
            throw new NoPictureSelectedException();
        }
    }

    private static void checkNameFieldIsNotEmpty(String name) throws NameFieldEmptyException {
        if(name.equals("")) {
            throw new NameFieldEmptyException();
        }
    }

    private static void checkNameIsUnic(String name) throws NameAlreadyUsedException {
        for (Item item : itemRepository.find()) {
            if (Objects.equals(name, item.getName()))
                throw new NameAlreadyUsedException(name);
        }
    }

    public static List<Item> getMyItems() throws FileNotFoundException {
        List<Item> myItems = new ArrayList<>();
        FileInputStream fileIn = new FileInputStream("log.txt");
        Scanner scan = new Scanner(fileIn);
        String fartist = scan.next();
        for (Item item : itemRepository.find(eq("artist",fartist))) {
                myItems.add(item);
        }
        return myItems;
    }

    public static List<Item> getItems()
    {
        return itemRepository.find().toList();
    }

    public static Item getItemByName(String name) throws ItemDoesNotExist, FileNotFoundException {
        FileInputStream fileIn = new FileInputStream("log.txt");
        Scanner scan = new Scanner(fileIn);
        String fartist = scan.next();
        for (Item item : itemRepository.find(and(eq("name",name),eq("artist",fartist)))) {
            return item;
        }
        throw new ItemDoesNotExist();
    }

    public static void deleteItem() throws FileNotFoundException, UserDoesNotExist {
        FileInputStream fileIn1 = new FileInputStream("log.txt");
        Scanner scan1 = new Scanner(fileIn1);
        String fartist1 = scan1.next();
        FileInputStream fileIn2 = new FileInputStream("iname.txt");
        Scanner scan2 = new Scanner(fileIn2);
        String fname1 = scan2.next();
//       itemRepository.remove(ObjectFilters.ALL); //used because of nasty first try of creating database
        itemRepository.remove(and(eq("name", fname1),eq("artist",fartist1)));

    }

}
