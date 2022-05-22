package ArtShoppingApplication.exceptions;

public class DescriptionFieldEmptyException extends Exception{
    public DescriptionFieldEmptyException(){
        super(String.format("Add a description!"));
    }
}
