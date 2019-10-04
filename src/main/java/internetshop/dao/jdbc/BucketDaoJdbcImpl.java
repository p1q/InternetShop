package internetshop.dao.jdbc;

import internetshop.annotations.Dao;
import internetshop.dao.BucketDao;
import internetshop.model.Bucket;
import internetshop.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {
    private static final Logger LOGGER = Logger.getLogger(BucketDaoJdbcImpl.class);

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) {
        String query = "INSERT INTO buckets(user_id) VALUES(?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, bucket.getUserId().toString());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to create the bucket");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    bucket.setBucketId(generatedKeys.getLong(1));
                } else {
                    LOGGER.error("Error obtaining bucket ID.");
                    throw new SQLException("Error obtaining bucket ID.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to create the bucket");
        }
        return bucket;
    }

    @Override
    public Bucket get(Long bucketId) {
        Bucket bucket = new Bucket();
        String query = "SELECT * FROM buckets WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            bucket.setUserId(resultSet.getLong("user_id"));
            bucket.setBucketId(bucketId);
        } catch (SQLException e) {
            LOGGER.error("Failed to get bucket with ID: " + bucketId);
        }
        bucket.setItems(getAllItems(bucket.getBucketId()));
        return bucket;
    }

    @Override
    public List getAllItems(Long bucketId) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM items INNER JOIN buckets_items ON bucket_id=?"
                + " AND items.item_id=buckets_items.item_id;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item(itemId, name, price);
                items.add(item);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return items;
    }

    @Override
    public Bucket getByUserId(Long userId) {
        long bucketId = 0;
        String query = "SELECT bucket_id FROM buckets WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            bucketId = resultSet.getLong("bucket_id");
        } catch (SQLException e) {
            LOGGER.error("Failed to obtain bucket for user ID: " + userId);
        }
        return get(bucketId);
    }

    @Override
    public void addItem(Long bucketId, Long itemId) {
        String query = "INSERT INTO buckets_items(bucket_id, item_id) VALUES(?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, bucketId.toString());
            preparedStatement.setString(2, itemId.toString());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to add the item into the bucket");
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to create the bucket");
        }
    }

    @Override
    public void deleteItem(Long bucketId, Long itemId) {
        String query = "DELETE FROM buckets_items WHERE bucket_id = ? AND item_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, bucketId.toString());
            preparedStatement.setString(2, itemId.toString());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to delete the item from the bucket");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Long userId) {
        String query = "DELETE FROM buckets WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to delete the bucket");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void clear(Long bucketId) {
        String query = "DELETE FROM buckets_items WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to clear the bucket");
        }
    }
}
