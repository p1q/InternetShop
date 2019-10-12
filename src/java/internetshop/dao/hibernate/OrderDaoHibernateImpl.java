package internetshop.dao.hibernate;

import internetshop.annotations.Dao;
import internetshop.dao.OrderDao;
import internetshop.model.Order;
import internetshop.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class OrderDaoHibernateImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoHibernateImpl.class);

    @Override
    public Order create(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(order);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Error creating the order. ", e);
        }
        return order;
    }

    @Override
    public Optional<Order> get(Long orderId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Order order = session.get(Order.class, orderId);
            return Optional.of(order);
        } catch (HibernateException e) {
            LOGGER.error("Error retrieving the order. ", e);
        }
        LOGGER.error("Error retrieving the order.");
        return Optional.empty();
    }

    @Override
    public List getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Order> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Order.class);
            criteriaQuery.from(Order.class);
            orders = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            LOGGER.error("Error retrieving all orders. ", e);
        }
        return orders;
    }

    @Override
    public Order update(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Error updating the order. ", e);
        } finally {
            session.close();
        }
        return order;
    }

    @Override
    public void delete(Long orderId) {
        Order order = get(orderId).get();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Error deleting the order. ", e);
        } finally {
            session.close();
        }
    }
}
