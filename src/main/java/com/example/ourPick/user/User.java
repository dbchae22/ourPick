package com.example.ourPick.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

// JPA가 관리하는 엔터티(데이터베이스 테이블과 매핑되는 클래스)
@Entity
public class User {
    @Id
    // 기본 키를 자동 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String authority;
    private String identifier;
    private String provider;
    private String password;
    private String accessToken;
    private LocalDate createdAt;

}