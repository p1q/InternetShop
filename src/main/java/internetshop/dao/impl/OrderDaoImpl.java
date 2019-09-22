package internetshop.dao.impl;

import internetshop.annotations.Dao;
import internetshop.dao.OrderDao;
import internetshop.database.DataBase;
import internetshop.model.Order;
import java.util.List;
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
    public List getAll() {
        return DataBase.orders;
    }

    @Override
    public void setItems(int id, List items) {
        DataBase.orders.get(id).setItems(items);
    }

    @Override
    public Order update(Order order) {
        for (int i = 0; i < DataBase.orders.size(); i++) {
            if (DataBase.orders.get(i).getId().equals(order.getId())) {
                DataBase.orders.set(i, order);
                return order;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void delete(Long id) {
        DataBase.orders.removeIf(order -> order.getId().equals(id));
    }
}
