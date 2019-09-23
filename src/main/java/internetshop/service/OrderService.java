package internetshop.service;

import internetshop.model.Order;
import java.util.List;

public interface OrderService {
    Order checkout(Long bucketId, Long userId);

    Order add(Order order);

    Order get(Long id);

    List getAll();

    Order update(Order order);

    void delete(Long id);
}
