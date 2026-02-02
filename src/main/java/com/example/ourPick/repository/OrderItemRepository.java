package com.example.ourPick.repository;

import com.example.ourPick.domain.OrderItem;
import com.example.ourPick.domain.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

}


