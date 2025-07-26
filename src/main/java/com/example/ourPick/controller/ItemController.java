package com.example.ourPick.controller;

import com.example.ourPick.dto.ItemRequestRequest;
import com.example.ourPick.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    public final ItemService itemService;

    @PostMapping
    public void regItem(@RequestBody ItemRequestRequest item){
        itemService.regItem(item);
    }

}
    