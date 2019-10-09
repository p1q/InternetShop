package internetshop.dao;

import internetshop.exceptions.AuthenticationException;
import internetshop.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    User create(User user);

    User get(Long id);

    List getAllUsers();

    User update(User user);

    void delete(User user);

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);

    boolean isLoginExists(String login);
}
