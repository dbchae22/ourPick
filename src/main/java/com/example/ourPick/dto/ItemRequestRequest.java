package com.example.ourPick.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemRequestRequest {

  private String itemName;
  private String storeName;
  private int discountRate;
  private int price;
  private String mainPhoto;
  private String createdAt;

  @Override
  public String toString() {
    return "ItemRequestDTO{" +
        "itemName='" + itemName + '\'' +
        ", storeName='" + storeName + '\'' +
        ", discountRate=" + discountRate +
        ", price=" + price +
        ", mainPhoto='" + mainPhoto + '\'' +
        '}';
  }
}