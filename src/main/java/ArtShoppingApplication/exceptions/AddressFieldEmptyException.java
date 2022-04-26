package ArtShoppingApplication.exceptions;

public class AddressFieldEmptyException extends Exception {
    public AddressFieldEmptyException(){
        super(String.format("Add an address!"));
    }
}
