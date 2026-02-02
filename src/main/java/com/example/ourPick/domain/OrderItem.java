package com.example.ourPick.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_items")
public class OrderItem {

  @EmbeddedId
  private OrderItemId id;

  @Column(name = "item_id")
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

  public OrderItem(Long orderNo, Integer orderSeq, Integer itemId, Integer quantity,
      Integer itemPrice) {
    this.id = new OrderItemId(orderNo, orderSeq);
    this.itemId = itemId;
    this.quantity = quantity;
    this.itemPrice = itemPrice;
  }
}

