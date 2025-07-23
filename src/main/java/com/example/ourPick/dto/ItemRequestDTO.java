package com.example.ourPick.dto;

import lombok.*;

@Getter
@Setter
public class ItemRequestDTO {
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