package ArtShoppingApplication.services;

import ArtShoppingApplication.exceptions.*;
import ArtShoppingApplication.model.Item;
import ArtShoppingApplication.model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.Objects;

import static ArtShoppingApplication.services.FileSystemService.getPathToFile;
import static ArtShoppingApplication.services.UserService.searchByEmail;

public class ItemService {
    private static ObjectRepository<Item> itemRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registered-items.db").toFile())
                .openOrCreate("test", "test");
        itemRepository = database.getRepository(Item.class);
    }

    public static void addItem(String name, String picture, String description, String price, String category, String dimensions, String material, String colors, String weight, String artist) throws NameAlreadyUsedException, NameFieldEmptyException, NoPictureSelectedException, DescriptionFieldEmptyException, PriceFieldEmptyException, PriceNotValidException, CategoryNotSelectedException, DimensionsFieldEmptyException, WeightFieldEmptyException, WeightNotValidException, UserDoesNotExist {
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
        User uartist = searchByEmail(artist);
        itemRepository.insert(new Item(name, picture, description, iprice, category, dimensions, material, colors, iweight, uartist));
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
}
