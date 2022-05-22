package ArtShoppingApplication.exceptions;

public class NoPictureSelectedException extends Exception{
    public NoPictureSelectedException(){
        super(String.format("Select a picture!"));
    }

}
