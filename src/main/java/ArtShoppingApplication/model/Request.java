package ArtShoppingApplication.model;


import java.util.Objects;

public class Request {

    private String name;
    private String picture;
    private String description;
    private boolean accept=false;

    public Request(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Request() {
    }

    public String getName() {
        return this.name;
    }


    public String getDescription() {
        return this.description;
    }
}
