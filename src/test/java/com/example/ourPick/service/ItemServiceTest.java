package com.example.ourPick.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.example.ourPick.domain.Item;
import com.example.ourPick.dto.ItemRequestRequest;
import com.example.ourPick.dto.ItemResponse;
import com.example.ourPick.repository.ItemRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

  @Mock
  private ItemRepository itemRepository;

  @InjectMocks
  private ItemService itemService;

  private ItemRequestRequest itemRequest;

  @BeforeEach
  void setUp() {
    itemRequest = new ItemRequestRequest();
    itemRequest.setItemName("테스트 상품");
    itemRequest.setStoreName("테스트 매장");
    itemRequest.setDiscountRate(10);
    itemRequest.setPrice(10000);
    itemRequest.setMainPhoto("test.jpg");
  }

  @Test
  void 상품등록_저장메서드가한번만호출되는지검증() {
    ArgumentCaptor<Item> itemCaptor = ArgumentCaptor.forClass(Item.class);

    itemService.regItem(itemRequest);

    then(itemRepository).should(times(1)).save(itemCaptor.capture());
    Item savedItem = itemCaptor.getValue();

    assertEquals("테스트 상품", savedItem.getItemName());
    assertEquals("테스트 매장", savedItem.getStoreName());
    assertEquals(10, savedItem.getDiscountRate());
    assertEquals(10000, savedItem.getPrice());
    assertEquals("test.jpg", savedItem.getMainPhoto());
  }

  @Test
  void 상품목록조회_findAll호출하고ItemResponse리스트반환하는지검증() {
    Item item1 = new Item("상품1", "매장1", 10, 10000, "photo1.jpg");
    Item item2 = new Item("상품2", "매장2", 20, 20000, "photo2.jpg");
    List<Item> items = Arrays.asList(item1, item2);
    given(itemRepository.findAll()).willReturn(items);

    List<ItemResponse> result = itemService.getItems();

    then(itemRepository).should(times(1)).findAll();
    assertEquals(2, result.size());
    assertEquals("상품1", result.get(0).getItemName());
    assertEquals("매장1", result.get(0).getStoreName());
    assertEquals(10, result.get(0).getDiscountRate());
    assertEquals(10000, result.get(0).getPrice());
    assertEquals("photo1.jpg", result.get(0).getMainPhoto());
    assertEquals("상품2", result.get(1).getItemName());
    assertEquals("매장2", result.get(1).getStoreName());
    assertEquals(20, result.get(1).getDiscountRate());
    assertEquals(20000, result.get(1).getPrice());
    assertEquals("photo2.jpg", result.get(1).getMainPhoto());
  }
}

