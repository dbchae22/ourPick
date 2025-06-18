package com.example.ourPick.service;

import com.example.ourPick.domain.Item;
import com.example.ourPick.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//서비스 계층 선언
@Service
//final 필드를 자동으로 생성해줌
@RequiredArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();  // 모든 아이템을 DB에서 가져옴
    }
}
