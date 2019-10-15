package internetshop.service;

import internetshop.model.Bucket;
import internetshop.model.Item;
import java.util.List;
import java.util.Optional;

public interface BucketService {
    List<Item> getAllItems(Bucket bucket);

    Bucket create(Bucket bucket);

    void addItem(Bucket bucket, Item item);

    void deleteItem(Long bucketId, Long itemId);

    Optional<Bucket> get(Long bucketId);

    Optional<Bucket> getByUserId(Long userId);

    void delete(Long userId);

    void clear(Long bucketId);
}
