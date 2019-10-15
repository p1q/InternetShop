package internetshop.dao;

import internetshop.model.Item;
import java.util.List;
import java.util.Optional;

public interface ItemDao {
    Item create(Item item);

    Optional<Item> get(Long itemId);

    List getAll();

    Item update(Item item);

    void delete(Item item);
}
