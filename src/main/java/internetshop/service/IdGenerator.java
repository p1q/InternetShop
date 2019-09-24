package internetshop.service;

public class IdGenerator {
    private static long userId = 0;
    private static long itemId = 0;
    private static long orderId = 0;
    private static long bucketId = 0;
    private static long roleId = 0;

    public static long generateUserId() {
        return userId++;
    }

    public static long generateItemId() {
        return itemId++;
    }

    public static long generateOrderId() {
        return orderId++;
    }

    public static long generateBucketId() {
        return bucketId++;
    }

    public static long generateRoleId() {
        return roleId++;
    }
}
