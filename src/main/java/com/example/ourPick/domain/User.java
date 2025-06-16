package com.example.ourPick.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.*;

import java.time.LocalDate;

// JPA가 관리하는 엔터티(데이터베이스 테이블과 매핑되는 클래스)
@Entity
public class User {
    //@Id: 테이블의 Primary Key와 연결
    //@Embeddedid: 기본키가 복합키로 된 경우 사용
    @Id
    //@GeneratedValue: JPA 기본키 생성 전략 (식별자값 자동 생성)
    //@GeneratedValue의 4가지 전략
    //1)AUTO: 자동으로 IDENTITY, SEQUENCE, TABLE 中 택 1
    //2)IDENTITY: 기본 키 생성을 데이터베이스에 위임한다
    //3)SEQUENCE: DB의 시퀀스를 활용하여 Id값을 증가시킨다.
    //4)TABLE: 키 생성 전용 테이블을 하나 만들어서 데이터베이스 시퀀스를 흉내내는 전략
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
