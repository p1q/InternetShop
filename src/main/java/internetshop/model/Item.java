package internetshop.model;

import internetshop.service.IdGenerator;

public class Item {
    private Long itemId;
    private String name;
    private Double price;

    public Item(Long itemId, String name, Double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

    public Item(String name, Double price) {
        this.itemId = IdGenerator.generateItemId();
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return itemId;
    }

    public void setId(Long id) {
        this.itemId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item {"
                + "itemId=" + itemId
                + ", name='" + name + '\''
                + ", price=" + price + '}';
    }
}
