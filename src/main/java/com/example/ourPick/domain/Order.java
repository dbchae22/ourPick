package com.example.ourPick.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id", nullable = false)
  private Long orderId;

  @Column(name = "order_no", nullable = false, unique = true)
  private String orderNo;

  @Column(name = "user_id", nullable = false)
  private Integer userId;

  @Column(name = "total_price")
  private Integer totalPrice;

  @Column(name = "status")
  private String status;

  @Column(name = "payment_method")
  private String paymentMethod;

  @Column(name = "address")
  private String address;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public Order(String orderNo, Integer userId, Integer totalPrice, String status,
      String paymentMethod, String address) {
    this.orderNo = orderNo;
    this.userId = userId;
    this.totalPrice = totalPrice;
    this.status = status;
    this.paymentMethod = paymentMethod;
    this.address = address;
  }

  public Order(Long orderId, String orderNo, Integer userId, Integer totalPrice, String status,
      String paymentMethod, String address) {
    this.orderId = orderId;
    this.orderNo = orderNo;
    this.userId = userId;
    this.totalPrice = totalPrice;
    this.status = status;
    this.paymentMethod = paymentMethod;
    this.address = address;
  }
}