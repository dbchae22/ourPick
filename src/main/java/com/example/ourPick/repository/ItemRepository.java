package com.example.ourPick.repository;

import com.example.ourPick.domain.Item;
import com.example.ourPick.dto.ItemSearchResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

  @Query("SELECT i FROM Item i WHERE i.itemName LIKE %:keyword% OR i.storeName LIKE %:keyword%")
  List<ItemSearchResponse> getItemsBySearch(@Param("keyword") String keyword);
}
