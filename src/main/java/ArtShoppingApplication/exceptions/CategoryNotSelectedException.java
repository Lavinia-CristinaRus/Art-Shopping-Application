package ArtShoppingApplication.exceptions;

public class CategoryNotSelectedException extends Exception{
    public CategoryNotSelectedException(){
        super(String.format("Select a category!"));
    }

}
