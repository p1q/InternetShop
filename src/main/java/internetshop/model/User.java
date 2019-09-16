package internetshop.model;

import internetshop.service.IdGenerator;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private List<Order> orders;
    private Bucket bucket;

    public User() {
        this.id = IdGenerator.generateUserId();
        orders = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }
}
