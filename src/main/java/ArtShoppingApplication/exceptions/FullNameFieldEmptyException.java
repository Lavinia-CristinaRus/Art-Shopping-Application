package ArtShoppingApplication.exceptions;

public class FullNameFieldEmptyException extends Exception {
    public FullNameFieldEmptyException(){
        super(String.format("Add a name!"));
    }
}
