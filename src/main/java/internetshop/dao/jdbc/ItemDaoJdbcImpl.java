package internetshop.dao.jdbc;

import internetshop.annotations.Dao;
import internetshop.dao.ItemDao;
import internetshop.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static final Logger LOGGER = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        String query = "INSERT INTO items(name, price) VALUES(?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getPrice().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to add the item!");
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long itemId) {
        String query = "SELECT * FROM items WHERE item_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, itemId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            resultSet.close();
            return Optional.of(new Item(itemId, name, price));
        } catch (SQLException e) {
            LOGGER.error("Failed to get item with id" + itemId);
        }
        return Optional.empty();
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM items";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                items.add(new Item(itemId, name, price));
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to get all items");
        }
        return items;
    }

    @Override
    public Item update(Item item) {
        String query = "UPDATE items SET name = ?, price = ? WHERE item_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getPrice().toString());
            preparedStatement.setString(3, item.getId().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to update the item!");
        }
        return item;
    }

    @Override
    public void delete(Item item) {
        String query = "DELETE FROM items WHERE item_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, item.getId().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to delete the item!");
        }
    }
}
