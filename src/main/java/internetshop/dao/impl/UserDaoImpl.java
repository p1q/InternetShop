package internetshop.dao.impl;

import internetshop.dao.UserDao;
import internetshop.database.DataBase;
import internetshop.lib.Dao;
import internetshop.model.User;
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
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("User ID '"
                        + id + "' wasn't found!"));
    }

    @Override
    public User update(User userToUpdate) {
        User user = get(userToUpdate.getId());
        user.setBucket(userToUpdate.getBucket());
        user.setOrders(userToUpdate.getOrders());
        return user;
    }

    @Override
    public void delete(Long id) {
        DataBase.users
                .removeIf(user -> user.getId().equals(id));
    }
}
