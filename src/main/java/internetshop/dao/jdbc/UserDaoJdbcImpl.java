package internetshop.dao.jdbc;

import internetshop.annotations.Dao;
import internetshop.annotations.Inject;
import internetshop.dao.UserDao;
import internetshop.exceptions.AuthenticationException;
import internetshop.model.Bucket;
import internetshop.model.Role;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.util.HashUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoJdbcImpl.class);

    @Inject
    private static BucketService bucketService;

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users (name, surname, login, password, email, phone, token,"
                + " salt) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        long userRoleId = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getToken());
            preparedStatement.setBytes(8, user.getSalt());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.error("Failed to create the user.");
                throw new SQLException("Failed to create the user.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getLong(1));
                } else {
                    LOGGER.error("Error obtaining user ID.");
                    throw new SQLException("Error obtaining user ID.");
                }
            }

            query = "SELECT role_id FROM roles WHERE name ='USER'";
            ResultSet resultSet = preparedStatement.executeQuery(query);
            resultSet.next();
            userRoleId = resultSet.getLong("role_id");
            user.addRole(new Role(userRoleId, "USER"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        query = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUserId().toString());
            preparedStatement.setString(2, Long.toString(userRoleId));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        Bucket bucket = new Bucket();
        bucket.setUser(user);
        bucketService.create(bucket);
        return user;
    }

    @Override
    public User get(Long userId) {
        String query = "SELECT * FROM users WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getUserFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Failed to get user with ID: " + userId);
        }
        return null;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getLong("user_id"));
        user.setUserName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setToken(resultSet.getString("token"));
        user.setSalt(resultSet.getBytes("salt"));
        user.setRoles(getUserRoles(user.getUserId()));
        return user;
    }

    private Set<Role> getUserRoles(Long userId) {
        Set<Role> roles = new HashSet<>();
        String query = "SELECT * FROM roles INNER JOIN users_roles"
                + " ON roles.role_id=users_roles.role_id AND user_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long roleId = resultSet.getLong("role_id");
                String roleName = resultSet.getString("name");
                roles.add(new Role(roleId, roleName));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return roles;
    }

    @Override
    public List getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return users;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET name = ?, surname = ?, login = ?, password = ?,"
                + " email = ?, phone = ?, token = ?, salt = ? WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getToken());
            preparedStatement.setBytes(8, user.getSalt());
            preparedStatement.setLong(9, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public void delete(User user) {
        bucketService.delete(user.getUserId());
        String query = "DELETE FROM users_roles WHERE user_id=?; "
                + "DELETE FROM users WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, user.getUserId());
            preparedStatement.setLong(2, user.getUserId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to delete the user");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        String query = "SELECT user_id FROM users WHERE login=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long userId = resultSet.getLong("user_id");
                resultSet.close();
                User user = get(userId);
                String registeredUserHash = user.getPassword();
                String loginUserHash = HashUtil.hashPassword(password, user.getSalt());
                if (registeredUserHash.equals(loginUserHash)) {
                    return user;
                }
            }
            resultSet.close();
            throw new AuthenticationException("Invalid login or password");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public Optional<User> getByToken(String token) {
        String query = "SELECT user_id FROM users WHERE token=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long userId = resultSet.getLong("user_id");
                return Optional.of(get(userId));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.of(new User());
    }

    @Override
    public boolean isLoginExists(String login) {
        String query = "SELECT user_id FROM users WHERE login=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return true;
    }
}
