package com.example.ourPick.controller;

import com.example.ourPick.dto.ItemRequestRequest;
import com.example.ourPick.dto.ItemResponse;
import com.example.ourPick.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

  public final ItemService itemService;

  @PostMapping
  public void regItem(@RequestBody ItemRequestRequest item) {
    itemService.regItem(item);
  }

  @GetMapping
  public List<ItemResponse> getItems() {
    List<ItemResponse> results = itemService.getItems();
    return results;
  }

}
    