package internetshop.dao;

import internetshop.model.Item;
import java.util.List;

public interface ItemDao {
    Item create(Item item);

    Item get(Long id);

    List getAll();

    Item update(Item item);

    void delete(Item item);
}
