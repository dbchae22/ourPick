package com.example.ourPick.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;
    
    @Column(name = "item_name", nullable = false)
    private String itemName;
    
    @Column(name = "store_name")
    private String storeName;
    
    @Column(name = "discount_rate")
    private Integer discountRate;
    
    @Column(name = "price")
    private Integer price;
    
    @Column(name = "main_photo")
    private String mainPhoto;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Item(String itemName, String storeName, Integer discountRate, Integer price, String mainPhoto) {
        this.itemName = itemName;
        this.storeName = storeName;
        this.discountRate = discountRate;
        this.price = price;
        this.mainPhoto = mainPhoto;
    }
}
