package com.example.ourPick.repository;

import com.example.ourPick.domain.Item;
import com.example.ourPick.domain.QItem;
import com.example.ourPick.dto.ItemSearchResponse;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

@Component
public class ItemRepositoryCustomImpl extends QuerydslRepositorySupport implements
    ItemRepositoryCustom {

  public ItemRepositoryCustomImpl() {
    super(Item.class);
  }

  @Override
  public List<ItemSearchResponse> getItemsBySearch(String keyword) {
    QItem item = QItem.item;

    return from(item)
        .select(Projections.constructor(ItemSearchResponse.class,
            item.itemId,
            item.itemName,
            item.storeName,
            item.discountRate,
            item.price,
            item.mainPhoto
        ))
        .where(item.itemName.containsIgnoreCase(keyword)
            .or(item.storeName.containsIgnoreCase(keyword)))
        .orderBy(item.createdAt.desc())
        .limit(100)
        .fetch();
  }
}
