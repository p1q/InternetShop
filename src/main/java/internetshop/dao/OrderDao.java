package internetshop.dao;

import internetshop.model.Order;
import java.util.List;

public interface OrderDao {
    Order create(Order order);

    Order get(Long id);

    List getAllOrders();

    void delete(Long id);
}
