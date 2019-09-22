package internetshop;

import internetshop.annotations.Inject;
import internetshop.annotations.Injector;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.OrderService;
import internetshop.service.UserService;
import internetshop.service.impl.BucketServiceImpl;
import internetshop.service.impl.OrderServiceImpl;

public class Main {
    // Создаём сервисы
    @Inject
    private static UserService userService;
    @Inject
    private static ItemService itemService;
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Создаём товары
        Item phone = new Item("Phone", 457.6);
        Item tablet = new Item("Tablet", 328.4);
        Item watch = new Item("Watch", 214.1);
        itemService.create(phone);
        itemService.create(tablet);
        itemService.create(watch);

        // Создаём юзера Васю
        User userVasja = new User();
        userService.create(userVasja);

        // Создаём корзину для Васи
        Bucket bucket = new Bucket(userVasja);
        BucketService bucketService = new BucketServiceImpl();
        bucketService.create(bucket);

        // Добавляем товары в Васину корзину
        bucketService.addItem(bucket, phone);
        bucketService.addItem(bucket, tablet);
        bucketService.addItem(bucket, watch);

        // Оформляем заказ
        OrderService orderService = new OrderServiceImpl();
        orderService.checkout(bucket);
        //orderService.delete(bucket.getId());

        System.out.println(userVasja.getOrders().get(0).getItems());
    }
}
