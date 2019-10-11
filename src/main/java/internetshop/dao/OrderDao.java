package internetshop.dao;

import internetshop.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Order create(Order order);

    Optional<Order> get(Long orderId);

    List getAllOrders();

    Order update(Order order);

    void delete(Long orderId);
}
