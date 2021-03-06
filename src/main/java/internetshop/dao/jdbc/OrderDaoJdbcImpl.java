package internetshop.dao.jdbc;

import internetshop.annotations.Dao;
import internetshop.annotations.Inject;
import internetshop.dao.OrderDao;
import internetshop.exceptions.UserNotFoundException;
import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.model.User;
import internetshop.service.ItemService;
import internetshop.service.UserService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;

@Dao
public class OrderDaoJdbcImpl extends AbstractDao<Item> implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoJdbcImpl.class);

    @Inject
    private static ItemService itemService;
    @Inject
    private static UserService userService;

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id) VALUES (?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, order.getUser().getUserId().toString());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to create the order");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Error obtaining order ID.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        for (int i = 0; i < order.getItems().size(); i++) {
            query = "INSERT INTO orders_items (order_id, item_id) VALUES (?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, order.getOrderId().toString());
                preparedStatement.setString(2, order.getItems().get(i).getId().toString());
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Failed to create the order");
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return order;
    }

    @Override
    public Optional<Order> get(Long orderId) {
        Order order = new Order();
        String query = "SELECT * FROM orders WHERE order_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Optional<User> user = userService.get(resultSet.getLong("user_id"));
            if (user.isPresent()) {
                order.setUser(user.get());
                order.setOrderId(orderId);
            } else {
                throw new UserNotFoundException("Error retrieving the user.");
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to get bucket with ID: " + orderId + ". " + e);
        } catch (UserNotFoundException e) {
            LOGGER.error("Error retrieving the user. " + e);
        }
        System.out.println();
        order.setItems(getAllItems(orderId));
        return Optional.of(order);
    }

    private List<Item> getAllItems(Long orderId) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT item_id FROM orders_items WHERE order_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("item_id");
                Optional<Item> item = itemService.get(itemId);
                item.ifPresent(items::add);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error("Failed to get all items.");
        }
        return items;
    }

    @Override
    public List getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT order_id FROM orders;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long orderId = resultSet.getLong("order_id");
                orders.add(get(orderId).get());
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error("Failed to get all orders.");
        }
        return orders;
    }

    @Override
    public Order update(Order order) {
        String query = "UPDATE orders SET user_id = ? WHERE order_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, order.getUser().getUserId());
            preparedStatement.setLong(2, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to update the order", e);
        }
        return order;
    }

    @Override
    public void delete(Long orderId) {
        String query = "DELETE FROM orders WHERE order_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, orderId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to delete the order");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
