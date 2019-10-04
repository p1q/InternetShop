package internetshop.service;

import internetshop.model.Order;
import java.util.List;

public interface OrderService {
    Order checkout(Long bucketId, Long userId);

    Order create(Order order);

    Order get(Long id);

    List getAll();

    void delete(Long id);
}
