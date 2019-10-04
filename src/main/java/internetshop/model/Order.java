package internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long orderId;
    private List<Item> items;
    private Long userId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Order() {
        items = new ArrayList<>();
    }

    public Order(List<Item> items, Long userId) {
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
