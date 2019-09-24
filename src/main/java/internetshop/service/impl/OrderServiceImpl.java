package internetshop.service.impl;

import internetshop.annotations.Inject;
import internetshop.annotations.Service;
import internetshop.dao.OrderDao;
import internetshop.dao.UserDao;
import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.service.BucketService;
import internetshop.service.OrderService;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;
    @Inject
    private static UserDao userDao;
    @Inject
    private static BucketService bucketService;

    @Override
    public Order add(Order order) {
        return orderDao.add(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public List getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public Order checkout(Long bucketId, Long userId) {
        Order order = new Order(bucketService.get(bucketId).getItems(), userId);
        orderDao.add(order);
        userDao.get(userId).getOrders().add(order);
        List<Item> itemsCopy = new ArrayList<>(order.getItems());
        orderDao.setItems(order.getId().intValue(), itemsCopy);
        return order;
    }
}
