package ArtShoppingApplication.exceptions;

public class WeightNotValidException extends Exception{
    public WeightNotValidException(){
        super(String.format("Invalid format for weight: expected an integer!"));
    }

}
