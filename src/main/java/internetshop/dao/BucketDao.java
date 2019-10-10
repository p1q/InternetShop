package internetshop.dao;

import internetshop.model.Bucket;
import java.util.List;
import java.util.Optional;

public interface BucketDao {
    Bucket create(Bucket bucket);

    Bucket get(Long bucketId);

    List getAllItems(Long bucketId);

    Optional<Bucket> getByUserId(Long userId);

    void addItem(Long bucketId, Long itemId);

    void deleteItem(Long bucketId, Long itemId);

    void delete(Long bucketId);

    void clear(Long bucketId);
}
