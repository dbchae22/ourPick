package com.example.ourPick.repository;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.example.ourPick.domain.Item;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ItemRepositoryTest {

  @Mock
  private ItemRepository itemRepository;

  private Item testItem;

  @BeforeEach
  void setUp() {
    testItem = new Item("테스트 상품", "테스트 매장", 10, 10000, "test.jpg");
  }

  @Test
  @DisplayName("save 메서드가 한 번만 호출되는지 검증")
  void save_메서드가한번만호출되는지검증() {
    Item savedItem = new Item("테스트 상품", "테스트 매장", 10, 10000, "test.jpg");
    given(itemRepository.save(any(Item.class))).willReturn(savedItem);

    itemRepository.save(testItem);

    then(itemRepository).should(times(1)).save(any(Item.class));
  }


  @Test
  @DisplayName("findAll 메서드가 한 번만 호출되는지 검증")
  void findAll_메서드가한번만호출되는지검증() {
    Item item1 = new Item("상품1", "매장1", 10, 10000, "photo1.jpg");
    Item item2 = new Item("상품2", "매장2", 20, 20000, "photo2.jpg");
    List<Item> items = Arrays.asList(item1, item2);
    given(itemRepository.findAll()).willReturn(items);

    itemRepository.findAll();

    then(itemRepository).should(times(1)).findAll();
  }
}

