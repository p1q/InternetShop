package internetshop.dao.arraylist;

import internetshop.annotations.Dao;
import internetshop.dao.BucketDao;
import internetshop.database.DataBase;
import internetshop.model.Bucket;
import java.util.List;
import java.util.NoSuchElementException;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        DataBase.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Bucket get(Long id) {
        return DataBase.buckets.stream()
                .filter(bucket -> bucket.getBucketId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Bucket ID '" + id + "' wasn't found!"));
    }

    @Override
    public List getAllItems(Long bucketId) {
        return null;
    }

    @Override
    public Bucket getByUserId(Long userId) {
        return null;
    }

    @Override
    public void addItem(Long bucketId, Long itemId) {

    }

    @Override
    public void deleteItem(Long bucketId, Long itemId) {

    }

    public Bucket update(Bucket bucketToUpdate) {
        Bucket bucket = get(bucketToUpdate.getBucketId());
        bucket.setItems(bucketToUpdate.getItems());
        bucket.setUser(bucketToUpdate.getUser());
        return bucket;
    }

    @Override
    public void clear(Long bucketId) {

    }

    @Override
    public void delete(Long userId) {
        DataBase.buckets
                .removeIf(element -> element.equals(get(userId)));
    }
}
