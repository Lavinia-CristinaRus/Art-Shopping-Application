package ArtShoppingApplication.exceptions;

public class IncorrectPasswordException extends Exception {

    private String password;

    public IncorrectPasswordException(String password) {
        super(String.format("Wrong Password!"));
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
