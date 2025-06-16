package com.example.ourPick.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    //1대다
    @OneToMany
    //매핑할 외래키
    @JoinColumn(name="USER_ID")
    private int userId;
    private String totalPrice;
    private String status;
    private String paymentMethod;
    private String address;
    private LocalDate created_at;
    private LocalDate updated_at;
}