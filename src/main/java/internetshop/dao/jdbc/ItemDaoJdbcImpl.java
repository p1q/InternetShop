package internetshop.dao.jdbc;

import internetshop.annotations.Dao;
import internetshop.dao.ItemDao;
import internetshop.model.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static final String DB_NAME = "internetshop";
    private static final Logger LOGGER = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        String query = String.format("INSERT INTO %s.items (name, price) VALUES ('%s', '%s');",
                DB_NAME, item.getName(), item.getPrice());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return item;
        } catch (SQLException e) {
            LOGGER.error("Failed to add the item!");
        }
        return item;
    }

    @Override
    public Item get(Long id) {
        String query = String.format("SELECT * FROM %s.items where item_id = %d;", DB_NAME, id);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            long itemId = resultSet.getLong("item_id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            return new Item(itemId, name, price);
        } catch (SQLException e) {
            LOGGER.error("Failed to get item with id" + id);
        }
        return null;
    }

    @Override
    public List<Item>  getAll() {
        String query = String.format("SELECT * FROM %s.items", DB_NAME);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Item> items = new ArrayList<>();
            while (resultSet.next()) {
                long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                items.add(new Item(itemId, name, price));
            }
            return items;
        } catch (SQLException e) {
            LOGGER.error("Failed to get all items");
        }
        return Collections.emptyList();
    }

    @Override
    public Item update(Item item) {
        String query = String.format("UPDATE %s.items SET name = '%s', price = '%s'"
                + " WHERE item_id = '%d';", DB_NAME, item.getName(), item.getPrice(), item.getId());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return item;
        } catch (SQLException e) {
            LOGGER.error("Failed to update the item!");
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        String query = String.format("DELETE FROM %s.items where item_id = %d;", DB_NAME, id);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            LOGGER.error("Failed to delete the item!");
        }
    }
}
