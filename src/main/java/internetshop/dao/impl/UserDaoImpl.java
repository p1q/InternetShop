package internetshop.dao.impl;

import internetshop.annotations.Dao;
import internetshop.dao.UserDao;
import internetshop.database.DataBase;
import internetshop.model.User;
import java.util.List;
import java.util.NoSuchElementException;

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
}
