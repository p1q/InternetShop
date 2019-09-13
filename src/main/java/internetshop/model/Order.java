package internetshop.model;

import internetshop.service.IdGenerator;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private List<Item> items;
    private Long userId;
    private User user;

    public Order() {
        this.id = IdGenerator.generateOrderId();
        items = new ArrayList<>();
    }

    public Order(List<Item> items, User user) {
        this.id = IdGenerator.generateOrderId();
        this.items = items;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
