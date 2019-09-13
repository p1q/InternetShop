package internetshop.dao.impl;

import internetshop.dao.ItemDao;
import internetshop.database.DataBase;
import internetshop.lib.Dao;
import internetshop.model.Item;
import java.util.NoSuchElementException;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        DataBase.items.add(item);
        return item;
    }

    @Override
    public Item get(Long id) {
        return DataBase.items.stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Item ID '"
                        + id + "' wasn't found!"));
    }

    @Override
    public Item update(Item itemToUpdate) {
        Item item = get(itemToUpdate.getId());
        item.setName(itemToUpdate.getName());
        item.setPrice(itemToUpdate.getPrice());
        return item;
    }

    @Override
    public void delete(Long id) {
        DataBase.items
                .removeIf(item -> item.getId().equals(id));
    }

    @Override
    public void delete(Item item) {
        DataBase.items
                .removeIf(element -> element.equals(item));
    }
}
