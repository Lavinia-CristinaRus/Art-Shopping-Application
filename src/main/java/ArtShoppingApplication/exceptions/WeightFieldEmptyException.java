package ArtShoppingApplication.exceptions;

public class WeightFieldEmptyException extends Exception {
    public WeightFieldEmptyException(){
        super(String.format("Add weight value!"));
    }
}
