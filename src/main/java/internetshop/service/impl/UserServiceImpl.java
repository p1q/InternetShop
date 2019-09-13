package internetshop.service.impl;

import internetshop.dao.BucketDao;
import internetshop.dao.UserDao;
import internetshop.lib.Inject;
import internetshop.model.User;
import internetshop.service.Service;
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
        return userDao.get(user.getId()).getOrders();
    }

    @Override
    public User add(User user) {
        User newUser = userDao.add(user);
        bucketDao.create(newUser.getBucket());
        return newUser;
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
