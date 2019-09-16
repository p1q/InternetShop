package internetshop.service;

import internetshop.model.Bucket;
import internetshop.model.Item;
import java.util.List;

public interface BucketService {
    List<Item> getAllItems(Bucket bucket);

    Bucket add(Bucket bucket);

    Bucket addItem(Bucket bucket, Item item);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    void delete(Long id);

    Bucket clear(Bucket bucket);
}
