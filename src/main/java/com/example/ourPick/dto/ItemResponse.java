package com.example.ourPick.dto;

import com.example.ourPick.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemResponse {

  private int itemId;
  private String itemName;
  private String storeName;
  private int discountRate;
  private int price;
  private String mainPhoto;

  public static ItemResponse from(Item item) {
    return new ItemResponse(
        item.getItemId(),
        item.getItemName(),
        item.getStoreName(),
        item.getDiscountRate(),
        item.getPrice(),
        item.getMainPhoto()
    );
  }
}