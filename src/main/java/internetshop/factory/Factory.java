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
    public static ItemService getItemService() {
        return new ItemServiceImpl();
    }

    public static BucketService getBucketService() {
        return new BucketServiceImpl();
    }

    public static UserService getUserService() {
        return new UserServiceImpl();
    }

    public static OrderService getOrderService() {
        return new OrderServiceImpl();
    }

    public static BucketDao getBucketDao() {
        return new BucketDaoImpl();
    }

    public static ItemDao getItemDao() {
        return new ItemDaoImpl();
    }

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static OrderDao getOrderDao() {
        return new OrderDaoImpl();
    }
}
