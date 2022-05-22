package ArtShoppingApplication.model;

import java.util.Objects;

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
    private User artist;

    public Item(String name, String picture, String description, Integer price, String category, String dimensions, String material, String colors, Integer weight, User artist) {
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
    public String getName() {
        return this.name;
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

}
