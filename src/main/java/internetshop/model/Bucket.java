package internetshop.model;

import internetshop.service.IdGenerator;
import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long bucketId;
    private List<Item> items;
    private User user;

    public Bucket(User user) {
        this.bucketId = IdGenerator.generateBucketId();
        this.user = user;
        user.setBucket(this);
        items = new ArrayList<>();
    }

    public Long getId() {
        return bucketId;
    }

    public void setId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
