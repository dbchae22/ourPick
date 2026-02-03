package com.example.ourPick.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_items", uniqueConstraints = {
    @UniqueConstraint(name = "uk_order_item", columnNames = {"order_id", "item_id"})
})
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_item_id")
  private Long orderItemId;

  @Column(name = "order_id", nullable = false)
  private Long orderId;

  @Column(name = "item_id", nullable = false)
  private Integer itemId;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "item_price")
  private Integer itemPrice;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", insertable = false, updatable = false)
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id", insertable = false, updatable = false)
  private Item item;

  public OrderItem(Long orderId, Integer itemId, Integer quantity,
      Integer itemPrice) {
    this.orderId = orderId;
    this.itemId = itemId;
    this.quantity = quantity;
    this.itemPrice = itemPrice;
  }

  public OrderItem(Long orderItemId, Long orderId, Integer itemId, Integer quantity,
      Integer itemPrice) {
    this.orderItemId = orderItemId;
    this.orderId = orderId;
    this.itemId = itemId;
    this.quantity = quantity;
    this.itemPrice = itemPrice;
  }
}

