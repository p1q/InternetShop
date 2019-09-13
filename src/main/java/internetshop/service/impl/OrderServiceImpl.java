package internetshop.service.impl;

import internetshop.dao.OrderDao;
import internetshop.dao.UserDao;
import internetshop.lib.Inject;
import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.model.User;
import internetshop.service.OrderService;
import internetshop.service.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private static OrderDao orderDao;

    @Inject
    private static UserDao userDao;

    @Override
    public Order add(Order order) {
        return orderDao.add(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
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
    public Order checkout(List<Item> items, User user) {
        Order order = new Order(items, user);
        orderDao.add(order);
        userDao.get(user.getId()).getOrders().add(order);
        userDao.get(user.getId()).getBucket().getItems().clear();
        return order;
    }
}
