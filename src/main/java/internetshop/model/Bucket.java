package internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long bucketId;
    private Long userId;
    private List<Item> items;

    public Bucket() {
        items = new ArrayList<>();
    }

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
