package com.example.ourPick.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
  @Column(name = "order_no")
  private Long orderNo;

  @Column(name = "order_id")
  private String orderId;

  @Column(name = "user_id")
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

  @OneToMany(mappedBy = "order")
  private List<OrderItem> items = new ArrayList<>();

  public Order(String orderId, Integer userId, Integer totalPrice, String status,
      String paymentMethod, String address) {
    this.orderId = orderId;
    this.userId = userId;
    this.totalPrice = totalPrice;
    this.status = status;
    this.paymentMethod = paymentMethod;
    this.address = address;
  }

  public void setItems(List<OrderItem> items) {
    this.items = items;
  }
}