package ArtShoppingApplication.exceptions;

public class PriceFieldEmptyException extends Exception{
    public PriceFieldEmptyException(){
        super(String.format("Add a price!"));
    }
}
