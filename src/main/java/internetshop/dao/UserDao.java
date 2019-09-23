package internetshop.dao;

import internetshop.exceptions.AuthenticationException;
import internetshop.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    User add(User user);

    User get(Long id);

    List getAll();

    User update(User user);

    void delete(Long id);

    User login (String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);
}
