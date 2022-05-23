package ArtShoppingApplication.exceptions;

public class WrongPasswordException extends Throwable {
    public WrongPasswordException(){
        super(String.format("Wrong Password!"));
    }
}
