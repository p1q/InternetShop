package internetshop.factory;

import internetshop.dao.BucketDao;
import internetshop.dao.ItemDao;
import internetshop.dao.OrderDao;
import internetshop.dao.UserDao;
import internetshop.dao.jdbc.BucketDaoJdbcImpl;
import internetshop.dao.jdbc.OrderDaoJdbcImpl;
import internetshop.dao.jdbc.UserDaoJdbcImpl;
import internetshop.dao.jdbc.ItemDaoJdbcImpl;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.OrderService;
import internetshop.service.UserService;
import internetshop.service.impl.BucketServiceImpl;
import internetshop.service.impl.ItemServiceImpl;
import internetshop.service.impl.OrderServiceImpl;
import internetshop.service.impl.UserServiceImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class Factory {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_NAME = "internetshop";
    private static final String DATABASE_URL = "jdbc:mysql://192.168.1.34/"
            + DATABASE_NAME + "?allowMultiQueries=true";
    private static final String USER = "adm";
    private static final String PASSWORD = "qwerty";
    private static final Logger LOGGER = Logger.getLogger(Factory.class);

    private static ItemDao itemDao;
    private static ItemService itemService;
    private static BucketDao bucketDao;
    private static BucketService bucketService;
    private static OrderDao orderDao;
    private static UserDao userDao;
    private static OrderService orderService;
    private static UserService userService;
    private static Connection connection;

    static {
        try {
            LOGGER.info("Connecting to the database...");
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);;
            LOGGER.info("Connection to the database was established successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error(e);
        }
    }

    public static BucketDao getBucketDao() {
        return bucketDao != null ? bucketDao :
                (bucketDao = new BucketDaoJdbcImpl(connection));
    }

    public static BucketService getBucketService() {
        return bucketService != null ? bucketService :
                (bucketService = new BucketServiceImpl());
    }

    public static ItemDao getItemDao() {
        return itemDao != null ? itemDao :
                (itemDao = new ItemDaoJdbcImpl(connection));
    }

    public static ItemService getItemService() {
        return itemService != null ? itemService :
                (itemService = new ItemServiceImpl());
    }

    public static UserDao getUserDao() {
        return userDao != null ? userDao :
                (userDao = new UserDaoJdbcImpl(connection));
    }

    public static OrderDao getOrderDao() {
        return orderDao != null ? orderDao :
                (orderDao = new OrderDaoJdbcImpl(connection));
    }

    public static OrderService getOrderService() {
        return orderService != null ? orderService :
                (orderService = new OrderServiceImpl());
    }

    public static UserService getUserService() {
        return userService != null ? userService :
                (userService = new UserServiceImpl());
    }
}
