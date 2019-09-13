package internetshop.lib;

import internetshop.Main;
import internetshop.dao.BucketDao;
import internetshop.dao.ItemDao;
import internetshop.dao.OrderDao;
import internetshop.dao.UserDao;
import internetshop.factory.Factory;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.OrderService;
import internetshop.service.UserService;
import internetshop.service.impl.BucketServiceImpl;
import internetshop.service.impl.ItemServiceImpl;
import internetshop.service.impl.OrderServiceImpl;
import internetshop.service.impl.UserServiceImpl;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Injector {
    private static Map<Class, Object> daoFields = new HashMap<>();

    static {
        daoFields.put(ItemDao.class, Factory.getItemDao());
        daoFields.put(ItemService.class, Factory.getItemService());
        daoFields.put(UserDao.class, Factory.getUserDao());
        daoFields.put(UserService.class, Factory.getUserService());
        daoFields.put(OrderDao.class, Factory.getOrderDao());
        daoFields.put(OrderService.class, Factory.getOrderService());
        daoFields.put(BucketDao.class, Factory.getBucketDao());
        daoFields.put(BucketService.class, Factory.getBucketService());
    }

    public static void injectAll() throws IllegalAccessException {
        inject(ItemServiceImpl.class.getDeclaredFields());
        inject(UserServiceImpl.class.getDeclaredFields());
        inject(OrderServiceImpl.class.getDeclaredFields());
        inject(BucketServiceImpl.class.getDeclaredFields());
        inject(Main.class.getDeclaredFields());
    }

    private static void inject(Field[] fields) throws IllegalAccessException {
        for (Field field : fields) {
            if (field.getDeclaredAnnotation(Inject.class) != null) {
                field.setAccessible(true);
                field.set(null, daoFields.get(field.getType()));
            }
        }
    }
}
