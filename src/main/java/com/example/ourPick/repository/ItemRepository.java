package com.example.ourPick.repository;

import com.example.ourPick.domain.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

  @Query(value = "SELECT * FROM items WHERE MATCH(item_name, store_name) AGAINST(:searchKeyword IN BOOLEAN MODE)", nativeQuery = true)
  List<Item> searchItems(@Param("searchKeyword") String searchKeyword);
}
