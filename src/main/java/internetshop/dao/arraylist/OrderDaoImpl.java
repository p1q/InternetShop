package internetshop.dao.arraylist;

import internetshop.annotations.Dao;
import internetshop.dao.OrderDao;
import internetshop.database.DataBase;
import internetshop.model.Order;
import java.util.List;
import java.util.NoSuchElementException;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        DataBase.orders.add(order);
        return order;
    }

    @Override
    public Order get(Long id) {
        return DataBase.orders.stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Order ID '"
                        + id + "' wasn't found!"));
    }

    @Override
    public List getAllOrders() {
        return null;
    }

    @Override
    public void delete(Long id) {
        DataBase.orders
                .removeIf(order -> order.getId().equals(id));
    }
}
