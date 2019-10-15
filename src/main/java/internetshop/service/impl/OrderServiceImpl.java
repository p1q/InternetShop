package internetshop.service.impl;

import internetshop.annotations.Inject;
import internetshop.annotations.Service;
import internetshop.dao.OrderDao;
import internetshop.model.Bucket;
import internetshop.model.Order;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.service.OrderService;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;
    @Inject
    private static BucketService bucketService;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAllOrders();
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public Order checkout(Bucket bucket, User user) {
        Order order = new Order(bucket.getItems(), user);
        orderDao.create(order);
        return order;
    }
}
