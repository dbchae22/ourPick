package com.example.ourPick.controller;

import com.example.ourPick.dto.ItemRequestDTO;
import com.example.ourPick.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    public ItemService itemService;

    @PostMapping("/regItem")
    public int regItem(@RequestBody ItemRequestDTO item){
        int itemId = itemService.regItem(item);
        return itemId;
    }

}
    