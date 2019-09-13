package internetshop.model;

import internetshop.service.IdGenerator;
import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long id;
    private List<Item> items;
    private Long orderId;
    private Long userId;

    public Bucket() {
        this.id = IdGenerator.generateBucketId();
        items = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
