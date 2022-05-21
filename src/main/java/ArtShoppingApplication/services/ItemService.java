package ArtShoppingApplication.services;

import ArtShoppingApplication.model.Item;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import static ArtShoppingApplication.services.FileSystemService.getPathToFile;

public class ItemService {
    private static ObjectRepository<Item> itemRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registered-items.db").toFile())
                .openOrCreate("test", "test");
        itemRepository = database.getRepository(Item.class);
    }
}
