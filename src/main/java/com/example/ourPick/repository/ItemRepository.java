package com.example.ourPick.repository;

import com.example.ourPick.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//repository 선언
@Repository
//<엔터티 타입, ID 타입>
public interface ItemRepository extends JpaRepository<Item, String> {
    //save(entity) 저장 (insert/update)
    //findById(id) ID로 조회
    //findAll()	전체 조회
    //delete(entity) 삭제
    //count() 레코드 수
}
