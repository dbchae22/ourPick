package com.example.ourPick.dto;

import com.example.ourPick.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemSearchResponse {
    private String itemName;
    private String storeName;
    private int discountRate;
    private int price;
    private String mainPhoto;

    public static ItemSearchResponse from(Item item) {
        return new ItemSearchResponse(
                item.getItemName(),
                item.getStoreName(),
                item.getDiscountRate(),
                item.getPrice(),
                item.getMainPhoto()
        );
    }
}