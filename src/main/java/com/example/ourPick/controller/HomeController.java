package com.example.ourPick.controller;

import com.example.ourPick.dto.ItemResponse;
import com.example.ourPick.service.ItemService;
import java.util.List;
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
    // 임의의 상품 데이터
    ItemResponse item = new ItemResponse(
        itemId,
        "프리미엄 무선 이어폰",
        "테크스토어",
        20,
        89000,
        "/images/items/no-img.png"
    );
    model.addAttribute("item", item);
    return "item-detail";
  }
}

