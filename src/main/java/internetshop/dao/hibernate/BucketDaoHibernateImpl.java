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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            bucketId = (Long) session.save(bucket);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Error creating the bucket. ", e);
        } finally {
            session.close();
        }
        bucket.setBucketId(bucketId);
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Bucket WHERE bucketId=:bucketId");
            query.setParameter("bucketId", bucketId);
            Optional<Bucket> bucket = query.list().stream().findFirst();
            if (bucket.isPresent()) {
                return bucket;
            }
        } catch (HibernateException e) {
            LOGGER.error("Error retrieving the bucket. ", e);
        }
        LOGGER.error("Error retrieving the bucket.");
        return Optional.empty();
    }

    @Override
    public List getAllItems(Long bucketId) {
        return get(bucketId).get().getItems();
    }

    @Override
    public Optional<Bucket> getByUserId(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Bucket WHERE user.userId=:userId");
            query.setParameter("userId", userId);
            return query.list().stream().findFirst();
        } catch (HibernateException e) {
            LOGGER.error("Error retrieving the bucket. ", e);
        }
        LOGGER.error("Error retrieving the bucket.");
        return Optional.empty();
    }

    @Override
    public void addItem(Long bucketId, Long itemId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
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
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteItem(Long bucketId, Long itemId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
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
        } finally {
            session.close();
        }
    }

    @Override
    public void clear(Long bucketId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
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
        } finally {
            session.close();
        }
    }

    @Deprecated
    @Override
    public void delete(Long bucketId) {
        // Do nothing in this Hibernate implementation
    }
}
