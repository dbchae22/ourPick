package com.example.ourPick.service;

import com.example.ourPick.domain.Item;
import com.example.ourPick.dto.ItemRequestRequest;
import com.example.ourPick.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void regItem(ItemRequestRequest item) {
        Item itemEntity = new Item(
                item.getItemName(),
                item.getStoreName(),
                item.getDiscountRate(),
                item.getPrice(),
                item.getMainPhoto()
                );
    }
}