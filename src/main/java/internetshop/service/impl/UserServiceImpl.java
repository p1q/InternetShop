package internetshop.service.impl;

import internetshop.annotations.Inject;
import internetshop.annotations.Service;
import internetshop.dao.UserDao;
import internetshop.exceptions.AuthenticationException;
import internetshop.model.User;
import internetshop.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAllUsers();
    }

    @Override
    public User create(User user) {
        user.setToken(getToken());
        return userDao.create(user);
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
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public boolean isLoginExists(String login) {
        return userDao.isLoginExists(login);
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        return userDao.login(login, password);
    }

    @Override
    public Optional<User> getByToken(String token) {
        return userDao.getByToken(token);
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }
}
