package com.example.ourPick.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class OrderItem {
    private String orderId;
    private int orderSeq;
    private String itemId;
    private int quantity;
    private int itemPrice;
}
