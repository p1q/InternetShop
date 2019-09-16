package internetshop.dao;

import internetshop.model.Order;

public interface OrderDao {
    Order add(Order order);

    Order get(Long id);

    Order update(Order order);

    void delete(Long id);
}
