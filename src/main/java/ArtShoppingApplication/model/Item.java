package ArtShoppingApplication.model;

import ArtShoppingApplication.exceptions.UserDoesNotExist;

import java.util.Objects;

import static ArtShoppingApplication.services.UserService.searchByEmail;

public class Item {

    private String name;
    private String picture;
    private String description;
    private Integer price;
    private String category;
    private String dimensions;
    private String material;
    private String colors;
    private Integer weight;
    private String artist;

    public Item(String name, String picture, String description, Integer price, String category, String dimensions, String material, String colors, Integer weight, String artist) {
        this.name = name;
        this.picture = picture;
        this.description = description;
        this.price = price;
        this.category = category;
        this.dimensions = dimensions;
        this.material = material;
        this.colors = colors;
        this.weight = weight;
        this.artist = artist;
    }
    public Item() {
    }
    public String getName() {
        return this.name;
    }

    public String getArtist() {
        return this.artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtShoppingApplication.model.Item item = (ArtShoppingApplication.model.Item) o;

        if (!Objects.equals(name, item.name)) return false;
        if (!Objects.equals(artist, item.artist)) return false;
        return true;
    }

    @Override
    public String toString() {
        String text = null;
        text = "Description: " + this.description + '\n' + "Price: " + this.price + '\n' + "Category: " + this.category + '\n'+ "Dimensions: " + this.dimensions + '\n'+ "Materials: " + this.material + '\n'+ "Colors: " + this.colors + '\n'+ "Weight: " + this.weight + '\n'+ "Artist: " + this.artist;
        return text;
    }

    public String getPicture() {
        return this.picture;
    }

}
