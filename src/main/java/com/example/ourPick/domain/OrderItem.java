package com.example.ourPick.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_items")
@IdClass(OrderItemId.class)
public class OrderItem {
    @Id
    @Column(name = "order_id")
    private String orderId;
    
    @Id
    @Column(name = "order_seq")
    private Integer orderSeq;
    
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
    
    public OrderItem(String orderId, Integer orderSeq, Integer itemId, Integer quantity, Integer itemPrice) {
        this.orderId = orderId;
        this.orderSeq = orderSeq;
        this.itemId = itemId;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }
}

