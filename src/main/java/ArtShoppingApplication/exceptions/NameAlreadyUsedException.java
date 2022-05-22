package ArtShoppingApplication.exceptions;

public class NameAlreadyUsedException extends Exception {
    private String name;
    public NameAlreadyUsedException(String name) {
        super(String.format("You have already submitted an item with this name %s\nUse another name.", name));
        this.name = name;
    }
}
