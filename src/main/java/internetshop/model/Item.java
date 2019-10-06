package internetshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", columnDefinition = "INT")
    private Long itemId;
    @Column(name = "name")
    private String name;
    @Column(name = "price", columnDefinition = "DECIMAL")
    private Double price;

    public Item() {
    }

    public Item(Long itemId, String name, Double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

    public Item(String name, Double price) {
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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "Item {"
                + "itemId=" + itemId
                + ", name='" + name + '\''
                + ", price=" + price + '}';
    }
}
