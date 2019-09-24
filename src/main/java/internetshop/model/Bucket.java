package internetshop.model;

import internetshop.service.IdGenerator;
import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long bucketId;
    private List<Item> items;

    public Bucket() {
        this.bucketId = IdGenerator.generateBucketId();
        items = new ArrayList<>();
    }

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
