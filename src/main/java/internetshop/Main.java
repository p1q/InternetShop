package internetshop;

import internetshop.lib.Inject;
import internetshop.lib.Injector;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.OrderService;
import internetshop.service.UserService;

public class Main {
    static {
        try {
            Injector.injectAll();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Inject
    private static UserService userService;

    @Inject
    private static ItemService itemService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static BucketService bucketService;

    public static void main(String[] args) {
        Item phone = itemService.create(new Item("Phone", 457.6));
        Item tablet = itemService.create(new Item("Tablet", 328.4));
        Item watch = itemService.create(new Item("Watch", 214.1));

        User user = userService.add(new User());

        Bucket bucket = bucketService.get(user.getBucket().getId());

        bucketService.addItem(bucket, phone);
        bucketService.addItem(bucket, tablet);
        bucketService.addItem(bucket, watch);

        orderService.checkout(bucket.getItems(), user.getUser());

        System.out.println(user.getOrders().get(0).getItems());
    }
}
