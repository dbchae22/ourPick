package com.example.ourPick;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.ourPick.domain.Item;
import com.example.ourPick.dto.ItemResponse;
import com.example.ourPick.repository.ItemRepository;
import com.example.ourPick.service.ItemService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemUpdateTest {

  // Test Double == 대역 배우
  ItemRepository mock = mock();
  ItemService itemService = new ItemService(mock);

  @Test
  void itemUpdateTest() throws BadRequestException {
    int itemId = 1;
    String itemName = "minkuk";

    Item item1 = new Item("1234", "1234", 12, 123, "124");
    when(mock.findById(itemId)).thenReturn(Optional.of(item1));

    Item item = itemService.updateItemName(itemId, itemName);
    Assertions.assertEquals(itemName, item.getItemName());
  }
}
