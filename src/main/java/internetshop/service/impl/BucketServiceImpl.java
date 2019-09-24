package internetshop.service.impl;

import internetshop.annotations.Inject;
import internetshop.annotations.Service;
import internetshop.dao.BucketDao;
import internetshop.dao.ItemDao;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.service.BucketService;
import java.util.List;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BucketDao bucketDao;

    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long id) {
        return bucketDao.get(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public void delete(Long id) {
        bucketDao.delete(id);
    }

    @Override
    public Bucket addItem(Bucket bucket, Item item) {
        bucket.getItems().add(itemDao.get(item.getId()));
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket deleteItem(Bucket bucket, Item item) {
        bucket.getItems().remove((itemDao.get(item.getId())));
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket clear(Bucket bucket) {
        bucket.getItems().clear();
        return bucket;
    }

    @Override
    public List getAllItems(Bucket bucket) {
        return bucket.getItems();
    }
}
