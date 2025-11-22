package com.example.ourPick.controller;

import com.example.ourPick.dto.ItemResponse;
import com.example.ourPick.service.ItemService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HomeController {

  private final ItemService itemService;

  @GetMapping("/")
  public String home(Model model) {
    List<ItemResponse> items = itemService.getItems();
    model.addAttribute("items", items);
    return "index";
  }

  @GetMapping("/{itemId}")
  public String getItem(Model model, @PathVariable Integer itemId) {
    Optional<ItemResponse> item = itemService.getItemDetail(itemId);
    
    model.addAttribute("item", item.get());
    return "item-detail";
  }
}

