package com.example.ourPick.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchRequest {
    private String keyword;

    @Override
    public String toString() {
        return "ItemSearchRequest{" +
                "keyword='" + keyword + '\'' +
                '}';
    }
}