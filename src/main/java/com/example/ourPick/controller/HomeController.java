package com.example.ourPick.controller;

import com.example.ourPick.dto.ItemResponse;
import com.example.ourPick.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
}

