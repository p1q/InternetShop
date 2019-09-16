package internetshop.dao.impl;

import internetshop.dao.BucketDao;
import internetshop.database.DataBase;
import internetshop.lib.Dao;
import internetshop.model.Bucket;
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
                .filter(bucket -> bucket.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Bucket ID '" + id + "' wasn't found!"));
    }

    @Override
    public Bucket update(Bucket bucketToUpdate) {
        Bucket bucket = get(bucketToUpdate.getId());
        bucket.setItems(bucketToUpdate.getItems());
        bucket.setUserId(bucketToUpdate.getUserId());
        return bucket;
    }

    @Override
    public void delete(Long id) {
        DataBase.buckets.removeIf(bucket -> bucket.getId().equals(id));
    }

    @Override
    public void delete(Bucket bucket) {
        DataBase.buckets
                .removeIf(element -> element.equals(bucket));
    }
}
