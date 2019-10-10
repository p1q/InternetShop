package internetshop.dao.arraylist;

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
    public User create(User user) {
        DataBase.users.add(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.of(DataBase.users.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("User ID '"
                        + id + "' wasn't found!")));
    }

    @Override
    public List getAllUsers() {
        return null;
    }

    @Override
    public User update(User userToUpdate) {
        return get(userToUpdate.getUserId()).get();
    }

    @Override
    public void delete(User user) {
        DataBase.users
                .removeIf(u -> u.getUserId().equals(user.getUserId()));
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        return null;
    }

    @Override
    public Optional<User> getByToken(String token) {
        return Optional.empty();
    }

    @Override
    public boolean isLoginExists(String login) {
        return false;
    }
}
