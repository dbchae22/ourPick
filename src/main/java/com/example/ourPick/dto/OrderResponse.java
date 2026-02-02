package com.example.ourPick.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponse {

  private Long orderNo;
  private String orderId;
  private Integer userId;
  private Integer totalPrice;
  private String status;
  private String paymentMethod;
  private String address;
  private LocalDateTime createdAt;

}


