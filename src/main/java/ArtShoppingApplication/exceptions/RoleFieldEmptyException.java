package ArtShoppingApplication.exceptions;

public class RoleFieldEmptyException extends Exception {
    public RoleFieldEmptyException(){
        super(String.format("Select a role!"));
    }
}
