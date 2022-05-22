package ArtShoppingApplication.model;


import java.util.Objects;

public class Request {

    private String name;
    private String description;
    private int accept=0;

    public Request(String name, String description, int accept) {
        this.name = name;
        this.description = description;
        this.accept=accept;
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
