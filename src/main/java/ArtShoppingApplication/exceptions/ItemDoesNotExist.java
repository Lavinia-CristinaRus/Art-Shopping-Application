package ArtShoppingApplication.exceptions;

public class ItemDoesNotExist extends Exception {
    public ItemDoesNotExist() {
        super(String.format("Item does not exist"));
    }
}