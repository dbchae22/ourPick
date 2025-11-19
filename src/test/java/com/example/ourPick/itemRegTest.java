package com.example.ourPick;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.example.ourPick.dto.ItemRequestRequest;
import com.example.ourPick.repository.ItemRepository;
import com.example.ourPick.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class itemRegTest {

  @Mock
  private ItemRepository itemRepository;

  @InjectMocks
  private ItemService itemService;

  @Test
  void 상품을_등록한다() {
    ItemRequestRequest itemRequest = ItemRequestRequest.builder()
        .itemName("테스트 상품")
        .storeName("테스트 매장")
        .discountRate(10)
        .price(10000)
        .mainPhoto("test.jpg")
        .createdAt("2025-11-19")
        .build();

    itemService.regItem(itemRequest);

    verify(itemRepository).save(any());
  }
}
