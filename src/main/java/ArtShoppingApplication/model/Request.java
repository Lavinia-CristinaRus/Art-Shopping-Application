package ArtShoppingApplication.model;


import java.util.Objects;

public class Request {

    private String name;
    private String description;
    private String buyer;
    private int accept=0;

    public Request(String name, String description, int accept, String buyer) {
        this.name = name;
        this.description = description;
        this.buyer = buyer;
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

    public void setStatus(int status) {
        this.accept = status;
    }

    public String getBuyer() {
        return this.buyer;
    }

    public int getStatus(){return this.accept;}
}
