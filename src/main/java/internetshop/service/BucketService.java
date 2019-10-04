package internetshop.service;

import internetshop.model.Bucket;
import internetshop.model.Item;
import java.util.List;

public interface BucketService {
    List<Item> getAllItems(Bucket bucket);

    Bucket create(Bucket bucket);

    void addItem(Bucket bucket, Item item);

    void deleteItem(Long bucketId, Long itemId);

    Bucket get(Long bucketId);

    Bucket getByUserId(Long userId);

    void delete(Long userId);

    void clear(Long bucketId);
}
