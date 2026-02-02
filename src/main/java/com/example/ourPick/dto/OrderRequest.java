package com.example.ourPick.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class OrderRequest {
    private Integer userId;
    private Integer totalPrice;
    private String paymentMethod;
    private String address;
    private List<OrderItemRequest> itemList;

    @Getter
    public static class OrderItemRequest {
        private Integer itemId;
        private Integer quantity;
        private Integer itemPrice;
    }
}


