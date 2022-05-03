package ArtShoppingApplication.exceptions;

public class EmailFieldEmptyException extends Exception {
    public EmailFieldEmptyException(){
        super(String.format("Add E-mail!"));
    }
}
