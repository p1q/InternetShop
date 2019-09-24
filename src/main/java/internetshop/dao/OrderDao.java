package internetshop.dao;

import internetshop.model.Order;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    Order get(Long id);

    List getAll();

    void setItems(int id, List items);

    Order update(Order order);

    void delete(Long id);
}
