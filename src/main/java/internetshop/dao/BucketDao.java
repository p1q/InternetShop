package internetshop.dao;

import internetshop.model.Bucket;
import java.util.List;

public interface BucketDao {
    Bucket create(Bucket bucket);

    Bucket get(Long bucketId);

    List getAllItems(Long bucketId);

    Bucket getByUserId(Long userId);

    void addItem(Long bucketId, Long itemId);

    void deleteItem(Long bucketId, Long itemId);

    void delete(Long userId);

    void clear(Long bucketId);
}
