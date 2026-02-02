package com.example.ourPick.service;

import com.example.ourPick.domain.Order;
import com.example.ourPick.domain.OrderItem;
import com.example.ourPick.dto.OrderRequest;
import com.example.ourPick.dto.OrderResponse;
import com.example.ourPick.repository.OrderItemRepository;
import com.example.ourPick.repository.OrderRepository;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;

  @Transactional
  public OrderResponse createOrder(OrderRequest request) {
    validateRequest(request);

    String orderId = generateOrderId();

    Order order = new Order(
        orderId,
        request.getUserId(),
        request.getTotalPrice(),
        "PENDING",
        request.getPaymentMethod(),
        request.getAddress()
    );

    Order saved = orderRepository.save(order);

    List<OrderItem> orderItems = new ArrayList<>();
    List<OrderRequest.OrderItemRequest> items = request.getItemList();

    for (int i = 0; i < items.size(); i++) {
      OrderItem orderItem = new OrderItem(
          saved.getOrderNo(),
          i + 1,
          items.get(i).getItemId(),
          items.get(i).getQuantity(),
          items.get(i).getItemPrice()
      );
      orderItems.add(orderItem);
    }
    orderItemRepository.saveAll(orderItems);

    return new OrderResponse(
        saved.getOrderNo(),
        saved.getOrderId(),
        saved.getUserId(),
        saved.getTotalPrice(),
        saved.getStatus(),
        saved.getPaymentMethod(),
        saved.getAddress(),
        saved.getCreatedAt()
    );

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

  private String generateOrderId() {
    String PREFIX = "ORD";
    DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    LocalDate currentDate = LocalDate.now();
    String date = LocalDate.now().format(DATE_FORMAT);

    return "ORD-" + date + "-" + randomString();
  }

  private static final String CHARSET = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
  private static final SecureRandom RANDOM = new SecureRandom();
  private static final int RANDOM_LENGTH = 6;

  private static String randomString() {

    StringBuilder sb = new StringBuilder(RANDOM_LENGTH);
    for (int i = 0; i < RANDOM_LENGTH; i++) {
      sb.append(CHARSET.charAt(RANDOM.nextInt(CHARSET.length())));
    }
    return sb.toString();
  }
}
