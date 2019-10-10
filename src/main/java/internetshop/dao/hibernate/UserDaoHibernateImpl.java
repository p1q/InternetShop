package internetshop.dao.hibernate;

import internetshop.annotations.Dao;
import internetshop.annotations.Inject;
import internetshop.dao.UserDao;
import internetshop.exceptions.AuthenticationException;
import internetshop.model.Bucket;
import internetshop.model.Role;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.util.HashUtil;
import internetshop.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoHibernateImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoHibernateImpl.class);

    @Inject
    private static BucketService bucketService;

    @Override
    public User create(User user) {
        Long userId = null;
        Long userRoleId;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("SELECT roleId FROM Role WHERE roleName=:roleName");
            query.setParameter("roleName", Role.RoleName.USER);
            userRoleId = (Long) query.uniqueResult();
        }
        user.addRole(new Role(userRoleId, "USER"));

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            userId = (Long) session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Error creating the user. ", e);
        } finally {
            session.close();
        }

        user.setUserId(userId);
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        bucketService.create(bucket);
        return user;
    }

    @Override
    public Optional<User> get(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE userId=:userId");
            query.setParameter("userId", userId);
            return query.list().stream().findFirst();
        } catch (HibernateException e) {
            LOGGER.error("Error retrieving the user. ", e);
        }
        LOGGER.error("Error retrieving the user.");
        return Optional.empty();
    }

    @Override
    public List getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(User.class);
            criteriaQuery.from(User.class);
            users = session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            LOGGER.error("Error retrieving all users. ", e);
        }
        return users;
    }

    @Override
    public User update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Error updating the user. ", e);
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public void delete(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Error deleting the user. ", e);
        } finally {
            session.close();
        }
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE login=:login");
            query.setParameter("login", login);
            Optional<User> user = query.list().stream().findFirst();
            if (user.isPresent()) {
                String registeredUserHash = user.get().getPassword();
                String loginUserHash = HashUtil.hashPassword(password, user.get().getSalt());
                if (registeredUserHash.equals(loginUserHash)) {
                    return user.get();
                }
            }
        }
        LOGGER.error("Invalid login or password");
        throw new AuthenticationException("Invalid login or password");
    }

    @Override
    public Optional<User> getByToken(String token) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE token=:token");
            query.setParameter("token", token);
            return query.uniqueResultOptional();
        }
    }

    @Override
    public boolean isLoginExists(String login) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE login=:login");
            query.setParameter("login", login);
            Optional<User> user = query.list().stream().findFirst();
            return user.isPresent();
        }
    }
}
