package com.example.ourPick.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String itemName;
    private String storeName;
    private int discountRate;
    private int price;
    private String mainPhoto;
    private LocalDate createdAt;
}
