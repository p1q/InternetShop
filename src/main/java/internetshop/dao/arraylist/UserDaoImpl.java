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
    public User get(Long id) {
        return DataBase.users.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("User ID '"
                        + id + "' wasn't found!"));
    }

    @Override
    public List getAllUsers() {
        return null;
    }

    @Override
    public User update(User userToUpdate) {
        User user = get(userToUpdate.getUserId());
        return user;
    }

    @Override
    public void delete(Long id) {
        DataBase.users
                .removeIf(user -> user.getUserId().equals(id));
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
