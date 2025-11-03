package com.example.ourPick.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
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
}