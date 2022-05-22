package ArtShoppingApplication.exceptions;

public class PriceNotValidException extends Exception{
    public PriceNotValidException(){
        super(String.format("Invalid format for price: expected an integer!"));
    }

}
