package com.example.ourPick.service;

import com.example.ourPick.domain.Item;
import com.example.ourPick.dto.ItemRequestRequest;
import com.example.ourPick.dto.ItemResponse;
import com.example.ourPick.repository.ItemRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    itemRepository.save(itemEntity);
  }

  public List<ItemResponse> getItems() {
    final List<Item> items = itemRepository.findAll();
    return items.stream()
        .map(ItemResponse::from)
        .toList();
  }

  public Optional<ItemResponse> getItemDetail(int itemId) {
    Optional<ItemResponse> item = itemRepository.findById(itemId).map(ItemResponse::from);
    return item;
  }
}