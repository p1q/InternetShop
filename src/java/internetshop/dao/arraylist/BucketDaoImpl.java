package internetshop.dao.arraylist;

import internetshop.annotations.Dao;
import internetshop.dao.BucketDao;
import internetshop.database.DataBase;
import internetshop.model.Bucket;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        DataBase.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long id) {
        return Optional.of(DataBase.buckets.stream()
                .filter(bucket -> bucket.getBucketId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Bucket ID '" + id + "' wasn't found!")));
    }

    @Override
    public List getAllItems(Long bucketId) {
        // Do nothing in this implementation
        return null;
    }

    @Override
    public Optional<Bucket> getByUserId(Long userId) {
        // Do nothing in this implementation
        return Optional.empty();
    }

    @Override
    public void addItem(Long bucketId, Long itemId) {
        // Do nothing in this implementation
    }

    @Override
    public void deleteItem(Long bucketId, Long itemId) {
        // Do nothing in this implementation
    }

    public Bucket update(Bucket bucketToUpdate) {
        Bucket bucket = get(bucketToUpdate.getBucketId()).get();
        bucket.setItems(bucketToUpdate.getItems());
        bucket.setUser(bucketToUpdate.getUser());
        return bucket;
    }

    @Override
    public void clear(Long bucketId) {
        // Do nothing in this implementation
    }

    @Override
    public void delete(Long userId) {
        DataBase.buckets
                .removeIf(element -> element.equals(get(userId)));
    }
}
