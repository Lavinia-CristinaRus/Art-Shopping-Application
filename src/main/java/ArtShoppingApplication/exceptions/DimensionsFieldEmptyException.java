package ArtShoppingApplication.exceptions;

public class DimensionsFieldEmptyException extends Exception{
    public DimensionsFieldEmptyException(){
        super(String.format("Add dimensions!"));
    }

}
