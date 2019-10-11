package internetshop.service;

import internetshop.model.Bucket;
import internetshop.model.Order;
import internetshop.model.User;
import java.util.List;

public interface OrderService {
    Order checkout(Bucket bucket, User user);

    Order create(Order order);

    Order get(Long id);

    List getAll();

    void delete(Long id);
}
