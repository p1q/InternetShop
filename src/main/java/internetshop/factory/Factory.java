package internetshop.factory;

import internetshop.dao.BucketDao;
import internetshop.dao.ItemDao;
import internetshop.dao.OrderDao;
import internetshop.dao.UserDao;
import internetshop.dao.impl.BucketDaoImpl;
import internetshop.dao.impl.ItemDaoImpl;
import internetshop.dao.impl.OrderDaoImpl;
import internetshop.dao.impl.UserDaoImpl;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.OrderService;
import internetshop.service.UserService;
import internetshop.service.impl.BucketServiceImpl;
import internetshop.service.impl.ItemServiceImpl;
import internetshop.service.impl.OrderServiceImpl;
import internetshop.service.impl.UserServiceImpl;

public class Factory {
    private static ItemDao itemDao;
    private static ItemService itemService;
    private static BucketDao bucketDao;
    private static BucketService bucketService;
    private static OrderDao orderDao;
    private static UserDao userDao;
    private static OrderService orderService;
    private static UserService userService;

    public static BucketDao getBucketDao() {
        return bucketDao != null ? bucketDao :
                (bucketDao = new BucketDaoImpl());
    }

    public static BucketService getBucketService() {
        return bucketService != null ? bucketService :
                (bucketService = new BucketServiceImpl());
    }

    public static ItemDao getItemDao() {
        return itemDao != null ? itemDao :
                (itemDao = new ItemDaoImpl());
    }

    public static ItemService getItemService() {
        return itemService != null ? itemService :
                (itemService = new ItemServiceImpl());
    }

    public static UserDao getUserDao() {
        return userDao != null ? userDao :
                (userDao = new UserDaoImpl());
    }

    public static OrderDao getOrderDao() {
        return orderDao != null ? orderDao :
                (orderDao = new OrderDaoImpl());
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
