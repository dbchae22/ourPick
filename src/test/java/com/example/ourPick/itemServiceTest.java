package com.example.ourPick;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.ourPick.domain.Item;
import com.example.ourPick.dto.ItemRequestRequest;
import com.example.ourPick.dto.ItemResponse;
import com.example.ourPick.repository.ItemRepository;
import com.example.ourPick.service.ItemService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class itemServiceTest {

  @Mock
  private ItemRepository itemRepository;

  @InjectMocks
  private ItemService itemService;

  @Test
  @DisplayName("상품을_등록한다")
  void regItem_ShouldCallSave() {
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

  @Test
  @DisplayName("상품목록을_조회한다")
  void getItems_ShouldCallFindAll() {
    Item item1 = new Item("itemName1", "storeName1", 10, 10000, "mainPhoto1");
    Item item2 = new Item("itemName2", "storeName2", 20, 20000, "mainPhoto2");
    List<Item> itemList = Arrays.asList(item1, item2);

    when(itemRepository.findAll()).thenReturn(itemList);

    List<ItemResponse> items = itemService.getItems();

    Assertions.assertEquals(2, items.size());
    Assertions.assertEquals("itemName1", items.get(0).getItemName());
    Assertions.assertEquals("itemName2", items.get(1).getItemName());

    verify(itemRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("itemId를 통해서 item을 조회할 수 있다.")
  void findById() {
    Item item = new Item("itemName1", "storeName1", 10, 10000, "mainPhoto1");
    int itemId = 1;
    ReflectionTestUtils.setField(item, "itemId", itemId);

    when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
    Optional<ItemResponse> foundItem = itemService.getItemDetail(itemId);
    ItemResponse itemResponse = ItemResponse.from(item);

    Assertions.assertTrue(foundItem.isPresent());
    Assertions.assertEquals(itemResponse, foundItem.get());
  }

}
