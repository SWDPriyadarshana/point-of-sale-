package lk.eyepax.demo.service.impl;


import lk.eyepax.demo.dto.ItemDTO;
import lk.eyepax.demo.entity.Item;
import lk.eyepax.demo.repository.ItemRepository;
import lk.eyepax.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<ItemDTO> findAllItems() {
        List<Item> all = itemRepository.findAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();

        for (Item item : all) {
            itemDTOS.add(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
        }

        return itemDTOS;
    }

    @Override
    public ItemDTO findItem(String code) {
        Item item = itemRepository.findById(code).get();
        return new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
    }

    @Override
    public void saveItem(String code, ItemDTO itemDTO) {
        if (!code.equals(itemDTO.getCode())){
            throw new RuntimeException("Item Codes are mismatched");
        }
        itemRepository.save(new Item(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(String code, ItemDTO itemDTO) {
        if (itemRepository.existsById(code)){
            saveItem(code,itemDTO);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteItem(String code) {
        itemRepository.deleteById(code);
        return true;
    }

    @Override
    public long ItemsCount() {
        return itemRepository.count();
    }
}
