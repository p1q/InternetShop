package internetshop.service.impl;

import internetshop.annotations.Inject;
import internetshop.annotations.Service;
import internetshop.dao.BucketDao;
import internetshop.dao.UserDao;
import internetshop.database.DataBase;
import internetshop.model.User;
import internetshop.service.UserService;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private static UserDao userDao;

    @Inject
    private static BucketDao bucketDao;

    @Override
    public List getOrders(User user) {
        return userDao.get(user.getUserId()).getOrders();
    }

    @Override
    public List<User> getAll() {
        return DataBase.users;
    }

    @Override
    public User create(User user) {
        return userDao.add(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}
