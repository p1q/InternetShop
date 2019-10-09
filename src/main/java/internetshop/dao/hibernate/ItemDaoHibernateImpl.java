package internetshop.dao.hibernate;

import internetshop.annotations.Dao;
import internetshop.dao.ItemDao;
import internetshop.dao.jdbc.ItemDaoJdbcImpl;
import internetshop.model.Item;
import internetshop.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ItemDaoHibernateImpl implements ItemDao {
    private static final Logger LOGGER = Logger.getLogger(ItemDaoJdbcImpl.class);

    @Override
    public Item create(Item item) {
        Long itemId = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            itemId = (Long) session.save(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Error creating the item. ", e);
        }
        item.setId(itemId);
        return item;
    }

    @Override
    public Item get(Long itemId) {
        Item item = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            item = session.get(Item.class, itemId);
        } catch (Exception e) {
            LOGGER.error("Error retrieving the item. ", e);
        }
        return item;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Item> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Item.class);
            criteriaQuery.from(Item.class);
            items = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            LOGGER.error("Error retrieving all users. ", e);
        }
        return items;
    }

    @Override
    public Item update(Item item) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Error updating the item. ", e);
        }
        return item;
    }

    @Override
    public void delete(Item item) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Error deleting the item. ", e);
        }
    }
}
