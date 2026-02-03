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
    String orderNo = generateOrderNo();

    Order order = new Order(
        orderNo,
        request.getUserId(),
        request.getTotalPrice(),
        "PENDING",
        request.getPaymentMethod(),
        request.getAddress()
    );

    Order saved = orderRepository.save(order);

    List<OrderItem> orderItems = new ArrayList<>();
    List<OrderRequest.OrderItemRequest> items = request.getItemList();

    for (OrderRequest.OrderItemRequest itemRequest : items) {
      OrderItem orderItem = new OrderItem(
          saved.getOrderId(),
          itemRequest.getItemId(),
          itemRequest.getQuantity(),
          itemRequest.getItemPrice()
      );
      orderItems.add(orderItem);
    }
    orderItemRepository.saveAll(orderItems);

    return new OrderResponse(
        saved.getOrderId(),
        saved.getOrderNo(),
        saved.getUserId(),
        saved.getTotalPrice(),
        saved.getStatus(),
        saved.getPaymentMethod(),
        saved.getAddress(),
        saved.getCreatedAt()
    );

  }

  public String generateOrderNo() {
    DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
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
