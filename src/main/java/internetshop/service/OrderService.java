package internetshop.service;

import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.model.User;

import java.util.List;

public interface OrderService {
    Order checkout(List<Item> items, User user);

    Order add(Order order);

    Order get(Long id);

    Order update(Order order);

    void delete(Long id);
}
