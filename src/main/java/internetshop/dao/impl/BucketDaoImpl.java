package internetshop.dao.impl;

import internetshop.annotations.Dao;
import internetshop.dao.BucketDao;
import internetshop.database.DataBase;
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
                .filter(bucket -> bucket.getBucketId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Bucket ID '" + id + "' wasn't found!"));
    }

    @Override
    public Bucket update(Bucket bucket) {
        for (int i = 0; i < DataBase.buckets.size(); i++) {
            if (DataBase.buckets.get(i).getBucketId().equals(bucket.getBucketId())) {
                DataBase.buckets.set(i, bucket);
                return bucket;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void delete(Long id) {
       DataBase.buckets.removeIf(bucket -> bucket.getBucketId().equals(id));
    }

    @Override
    public void delete(Bucket bucket) {
        DataBase.buckets
                .removeIf(element -> element.equals(bucket));
    }
}
