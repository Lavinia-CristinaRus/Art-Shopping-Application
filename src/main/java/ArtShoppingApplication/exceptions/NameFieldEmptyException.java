package ArtShoppingApplication.exceptions;

public class NameFieldEmptyException extends Exception{
    public NameFieldEmptyException(){
        super(String.format("Add name!"));
    }
}
