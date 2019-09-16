package internetshop.dao;

import internetshop.model.User;

public interface UserDao {
    User add(User user);

    User get(Long id);

    User update(User user);

    void delete(Long id);
}
