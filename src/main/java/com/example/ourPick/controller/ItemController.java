package com.example.ourPick.controller;

import com.example.ourPick.dto.ItemRequestRequest;
import com.example.ourPick.dto.ItemResponse;
import com.example.ourPick.dto.ItemSearchResponse;
import com.example.ourPick.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
@Validated
public class ItemController {

  private final ItemService itemService;

  @PostMapping
  public ResponseEntity<Void> regItem(@Valid @RequestBody ItemRequestRequest item) {
    itemService.regItem(item);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public List<ItemResponse> getItems() {
    List<ItemResponse> results = itemService.getItems();
    return results;
  }

  @GetMapping("/search")
  public List<ItemSearchResponse> getItemsBySearch(
      @RequestParam @NotBlank(message = "검색 키워드는 필수입니다") String keyword) {
    return itemService.getItemsBySearch(keyword.trim());
  }

}
    