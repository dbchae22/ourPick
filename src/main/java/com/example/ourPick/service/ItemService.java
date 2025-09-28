package com.example.ourPick.service;

import com.example.ourPick.domain.Item;
import com.example.ourPick.dto.ItemRequestRequest;
import com.example.ourPick.dto.ItemResponse;
import com.example.ourPick.dto.ItemSearchResponse;
import com.example.ourPick.repository.ItemRepository;
import com.example.ourPick.repository.ItemRepositoryCustomImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;
  private final ItemRepositoryCustomImpl itemRepositoryCustom;

  @Transactional
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

  @Transactional(readOnly = true)
  public List<ItemResponse> getItems() {
    final List<Item> items = itemRepository.findAll();
    return items.stream()
        .map(ItemResponse::from)
        .toList();
  }

  @Transactional(readOnly = true)
  public List<ItemSearchResponse> getItemsBySearch(String keyword) {
    return itemRepositoryCustom.getItemsBySearch(keyword);
  }
}