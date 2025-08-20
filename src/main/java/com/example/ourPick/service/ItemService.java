package com.example.ourPick.service;

import com.example.ourPick.domain.Item;
import com.example.ourPick.dto.ItemRequestRequest;
import com.example.ourPick.dto.ItemResponse;
import com.example.ourPick.dto.ItemSearchResponse;
import com.example.ourPick.repository.ItemRepository;
import java.util.List;
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
  }

  public List<ItemResponse> getItems() {
    final List<Item> items = itemRepository.findAll();
    return items.stream()
        .map(ItemResponse::from)
        .toList();
  }

  public List<ItemSearchResponse> getItemsBySearch(String keyword) {
    final List<ItemSearchResponse> items = itemRepository.getItemsBySearch(keyword);
    return items;
  }
}