package com.example.ourPick.repository;

import com.example.ourPick.domain.Item;
import com.example.ourPick.dto.ItemSearchResponse;
import java.util.List;

public interface ItemRepositoryCustom {
  
  List<ItemSearchResponse> getItemsBySearch(String keyword);

}
