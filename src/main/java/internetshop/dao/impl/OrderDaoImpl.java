package internetshop.dao.impl;

import internetshop.dao.OrderDao;
import internetshop.database.DataBase;
import internetshop.lib.Dao;
import internetshop.model.Order;
import java.util.NoSuchElementException;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order add(Order order) {
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
    public Order update(Order orderToUpdate) {
        Order order = get(orderToUpdate.getId());
        order.setItems(orderToUpdate.getItems());
        order.setUserId(orderToUpdate.getUserId());
        return order;
    }

    @Override
    public void delete(Long id) {
        DataBase.orders
                .removeIf(order -> order.getId().equals(id));
    }

}
