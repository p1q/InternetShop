package internetshop.dao.hibernate;

import internetshop.annotations.Dao;
import internetshop.annotations.Inject;
import internetshop.dao.BucketDao;
import internetshop.model.Bucket;
import internetshop.service.ItemService;
import internetshop.util.HibernateUtil;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class BucketDaoHibernateImpl implements BucketDao {
    private static final Logger LOGGER = Logger.getLogger(BucketDaoHibernateImpl.class);

    @Inject
    private static ItemService itemService;

    @Override
    public Bucket create(Bucket bucket) {
        Long bucketId = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            bucketId = (Long) session.save(bucket);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Error creating the bucket. ", e);
        }
        bucket.setBucketId(bucketId);
        return bucket;
    }

    @Override
    public Bucket get(Long bucketId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Bucket WHERE bucketId=:bucketId");
            query.setParameter("bucketId", bucketId);
            Optional<Bucket> bucket = query.list().stream().findFirst();
            if (bucket.isPresent()) {
                return bucket.get();
            }
        } catch (HibernateException e) {
            LOGGER.error("Error retrieving the bucket. ", e);
        }
        LOGGER.error("Error retrieving the bucket.");
        return null;
    }

    @Override
    public List getAllItems(Long bucketId) {
        return get(bucketId).getItems();
    }

    @Override
    public Bucket getByUserId(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Bucket WHERE user.userId=:userId");
            query.setParameter("userId", userId);
            Optional<Bucket> bucket = query.list().stream().findFirst();
            if (bucket.isPresent()) {
                return bucket.get();
            }
        } catch (HibernateException e) {
            LOGGER.error("Error retrieving the bucket. ", e);
        }
        LOGGER.error("Error retrieving the bucket.");
        return null;
    }

    @Override
    public void addItem(Long bucketId, Long itemId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(
                    "INSERT INTO buckets_items(bucket_id, item_id) VALUES(?, ?);");
            query.setParameter(1, bucketId);
            query.setParameter(2, itemId);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Failed to add the item into the bucket");
        }
    }

    @Override
    public void deleteItem(Long bucketId, Long itemId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(
                    "DELETE FROM buckets_items WHERE bucket_id = ? AND item_id = ?;");
            query.setParameter(1, bucketId);
            query.setParameter(2, itemId);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Failed to delete the item from the bucket");
        }
    }

    @Override
    public void clear(Long bucketId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(
                    "DELETE FROM buckets_items WHERE bucket_id = ?;");
            query.setParameter(1, bucketId);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Failed to clear the bucket");
        }
    }

    @Deprecated
    @Override
    public void delete(Long userId) {
    }
}
