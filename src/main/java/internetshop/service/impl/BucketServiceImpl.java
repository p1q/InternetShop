package internetshop.service.impl;

import internetshop.annotations.Inject;
import internetshop.annotations.Service;
import internetshop.dao.BucketDao;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.service.BucketService;
import java.util.List;
import java.util.Optional;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private static BucketDao bucketDao;

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long id) {
        return bucketDao.get(id).get();
    }

    @Override
    public Optional<Bucket> getByUserId(Long userId) {
        return bucketDao.getByUserId(userId);
    }

    @Override
    public void addItem(Bucket bucket, Item item) {
        bucketDao.addItem(bucket.getBucketId(), item.getId());
    }

    @Override
    public void deleteItem(Long bucketId, Long itemId) {
        bucketDao.deleteItem(bucketId, itemId);
    }

    @Override
    public List getAllItems(Bucket bucket) {
        return bucketDao.getAllItems(bucket.getBucketId());
    }

    @Override
    public void clear(Long bucketId) {
        bucketDao.clear(bucketId);
    }

    @Deprecated
    @Override
    public void delete(Long userId) {
        bucketDao.delete(userId);
    }
}
