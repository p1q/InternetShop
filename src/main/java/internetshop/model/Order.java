package internetshop.model;

import internetshop.service.IdGenerator;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long orderId;
    private List<Item> items;
    private Long userId;

    public Order() {
        this.orderId = IdGenerator.generateOrderId();
        items = new ArrayList<>();
    }

    public Order(List<Item> items, Long userId) {
        this.orderId = IdGenerator.generateOrderId();
        this.items = items;
        this.userId = userId;
    }

    public Long getId() {
        return orderId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + orderId + ", userId=" + userId + ", items=" + items + '}';
    }
}
