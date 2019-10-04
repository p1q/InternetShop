package internetshop.service.impl;

import internetshop.annotations.Inject;
import internetshop.annotations.Service;
import internetshop.dao.OrderDao;
import internetshop.model.Order;
import internetshop.service.BucketService;
import internetshop.service.OrderService;
import java.util.List;

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
    public Order get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public List getAll() {
        return orderDao.getAllOrders();
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public Order checkout(Long bucketId, Long userId) {
        Order order = new Order(bucketService.get(bucketId).getItems(), userId);
        orderDao.create(order);
        return order;
    }
}
