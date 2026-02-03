package com.example.ourPick.controller;

import com.example.ourPick.dto.OrderRequest;
import com.example.ourPick.dto.OrderResponse;
import com.example.ourPick.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
    validateRequest(request);
    OrderResponse response = orderService.createOrder(request);
    return ResponseEntity.ok(response);
  }

  private void validateRequest(OrderRequest request) {
    if (request == null) {
      throw new IllegalArgumentException("주문 요청 정보가 없습니다.");
    }
    if (request.getUserId() == null) {
      throw new IllegalArgumentException("사용자 ID가 필요합니다.");
    }
    if (request.getTotalPrice() == null || request.getTotalPrice() < 0) {
      throw new IllegalArgumentException("유효한 총 가격이 필요합니다.");
    }
    if (request.getItemList() == null || request.getItemList().isEmpty()) {
      throw new IllegalArgumentException("주문 항목이 필요합니다.");
    }
    for (OrderRequest.OrderItemRequest item : request.getItemList()) {
      if (item.getItemId() == null || item.getQuantity() == null || item.getQuantity() <= 0) {
        throw new IllegalArgumentException("주문 항목 정보가 올바르지 않습니다.");
      }
    }
  }
}
