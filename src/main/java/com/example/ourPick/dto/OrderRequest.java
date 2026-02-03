package com.example.ourPick.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

  private Integer userId;
  private Integer totalPrice;
  private String paymentMethod;
  private String address;
  private List<OrderItemRequest> itemList;

  @Getter
  @Setter
  public static class OrderItemRequest {

    private Integer itemId;
    private Integer quantity;
    private Integer itemPrice;
  }
}


