package com.example.ourPick.domain;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String itemName;
    private String storeName;
    private int discountRate;
    private int price;
    private String mainPhoto;
    @CreationTimestamp
    private LocalDate createdAt;

    public Item(String itemName, String storeName, int discountRate, int price, String mainPhoto) {
        this.itemName = itemName;
        this.storeName = storeName;
        this.discountRate = discountRate;
        this.price = price;
        this.mainPhoto = mainPhoto;
    }
}
