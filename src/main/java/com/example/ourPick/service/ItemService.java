package com.example.ourPick.service;

import com.example.ourPick.domain.Item;
import com.example.ourPick.dto.ItemRequestRequest;
import com.example.ourPick.dto.ItemResponse;
import com.example.ourPick.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void regItem(ItemRequestRequest item) {
        Item itemEntity = new Item(
                item.getItemName(),
                item.getStoreName(),
                item.getDiscountRate(),
                item.getPrice(),
                item.getMainPhoto()
                );
    }

    public List<ItemResponse> getItems() {
        final List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(ItemResponse::from)
                .toList();
    }

    public Item updateItemName(int itemId, String itemName) throws BadRequestException {
        Item item = itemRepository.findById(itemId).orElseThrow(BadRequestException::new);
        item.setItemName(itemName);
        return item;
    }
}