package internetshop.dao.jdbc;

import internetshop.annotations.Dao;
import internetshop.dao.ItemDao;
import internetshop.model.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static String DB_NAME = "internetshop";
    private static final Logger LOGGER = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        String query = "INSERT INTO " + DB_NAME + ".items (name, price) VALUES ('"
                + item.getName() + "', '" + item.getPrice() + "');";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return item;
        } catch (SQLException e) {
            LOGGER.warn("Failed to add the item!");
        }
        return item;
    }

    @Override
    public Item get(Long id) {
        String query = "SELECT * FROM " + DB_NAME + ".items where item_id = " + id + ";";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            long itemId = resultSet.getLong("item_id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            return new Item(itemId, name, price);
        } catch (SQLException e) {
            LOGGER.warn("Failed to get item with id" + id);
        }
        return null;
    }

    @Override
    public List getAll() {
        String query = "SELECT * FROM " + DB_NAME + ".items";
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
            LOGGER.warn("Failed to get all items");
        }
        return null;
    }

    @Override
    public Item update(Item item) {
        String query = "UPDATE " + DB_NAME + ".items SET name = " + "'" + item.getName()
                + "'" + ", price = '" + item.getPrice() + "' WHERE item_id = '"
                + item.getId() + "';";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return item;
        } catch (SQLException e) {
            LOGGER.warn("Failed to update the item!");
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM " + DB_NAME + ".items where item_id = " + id + ";";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            LOGGER.warn("Failed to delete the item!");
        }
    }
}
