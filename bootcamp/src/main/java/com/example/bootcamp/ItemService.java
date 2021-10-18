package com.example.bootcamp;

import com.example.bootcamp.exception.ItemNotAvailableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private ItemsRepository itemsRepository;

    public ItemService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        itemList = itemsRepository.findAll();
        return itemList;
    }

    public Integer saveItem(Item item) {
        Item item1 = itemsRepository.save(item);
        return item1.getItemNumber();
    }

    //either there is item or not for the provided id on db
    public String updateItem(Item item, Integer itemNumber) throws ItemNotAvailableException {
        Optional<Item> item1 = itemsRepository.findById(itemNumber);
        if (item1.isEmpty()) {
            throw new ItemNotAvailableException("Item with " + itemNumber + " not available");
        }
        item1.get().setItemDescription(item.getItemDescription());
        item1.get().setItemPrice((float) item.getItemPrice());
        itemsRepository.save(item1.get());
        return "Item Updated";
    }

    public void deleteById(int id) {
        itemsRepository.deleteById(id);
    }

}
