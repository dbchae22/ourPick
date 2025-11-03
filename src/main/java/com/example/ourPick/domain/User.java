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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "username", nullable = false)
    private String username;
    
    @Column(name = "authority")
    private String authority;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "identifier")
    private String identifier;
    
    @Column(name = "provider")
    private String provider;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "access_token")
    private String accessToken;
}