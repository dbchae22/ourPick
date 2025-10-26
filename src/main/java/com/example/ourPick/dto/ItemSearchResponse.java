package com.example.ourPick.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemSearchResponse {

  private int itemId;
  private String itemName;
  private String storeName;
  private int discountRate;
  private int price;
  private String mainPhoto;

}