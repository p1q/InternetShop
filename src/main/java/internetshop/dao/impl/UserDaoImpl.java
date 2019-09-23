package internetshop.dao.impl;

import internetshop.annotations.Dao;
import internetshop.dao.UserDao;
import internetshop.database.DataBase;
import internetshop.exceptions.AuthenticationException;
import internetshop.model.User;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User add(User user) {
        DataBase.users.add(user);
        return user;
    }

    @Override
    public User get(Long id) {
        return DataBase.users.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("User ID '"
                        + id + "' wasn't found!"));
    }

    @Override
    public List getAll() {
        return DataBase.users;
    }

    @Override
    public User update(User user) {
        for (int i = 0; i < DataBase.users.size(); i++) {
            if (DataBase.users.get(i).getUserId().equals(user.getUserId())) {
                DataBase.users.set(i, user);
                return user;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void delete(Long id) {
        DataBase.users
                .removeIf(user -> user.getUserId().equals(id));
    }

    @Override
    public User login (String login, String password) throws AuthenticationException {
        Optional<User> user = DataBase.users.stream()
                .filter(u -> u.getLogin().equals(login)).findFirst();
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new AuthenticationException("Invalid login or password");
        }
        return user.get();
    }

    @Override
    public Optional<User> getByToken(String token) {
        return DataBase.users.stream()
                .filter(u -> u.getToken().equals(token)).findFirst();
    }
}
