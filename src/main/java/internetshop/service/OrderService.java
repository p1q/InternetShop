package internetshop.service;

import internetshop.model.Bucket;
import internetshop.model.Order;
import java.util.List;

public interface OrderService {
    Order checkout(Bucket bucket);

    Order add(Order order);

    Order get(Long id);

    List getAll();

    Order update(Order order);

    void delete(Long id);
}
