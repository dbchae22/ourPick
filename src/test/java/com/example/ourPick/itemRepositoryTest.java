package com.example.ourPick;

import com.example.ourPick.domain.Item;
import com.example.ourPick.repository.ItemRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class ItemRepositoryTest {

  @Autowired
  private ItemRepository itemRepository;

  @BeforeEach
  void setUp() {
    itemRepository.save(new Item("아이폰 15", "애플스토어", 10, 10000, "mainPhoto1"));
    itemRepository.save(new Item("갤럭시 S24", "삼성스토어", 20, 20000, "mainPhoto2"));
    itemRepository.save(new Item("아이폰 케이스", "쿠팡", 30, 30000, "mainPhoto3"));
  }

  @AfterEach
  void cleanup() {
    itemRepository.deleteAll();
  }

  @Test
  @DisplayName("아이폰 검색 결과 조회")
  void searchItems_success() {
    List<Item> result = itemRepository.searchItems("아이폰");

    assertThat(result).hasSize(2)
        .extracting("itemName")
        .containsExactlyInAnyOrder("아이폰 15", "아이폰 케이스");
  }

  @Test
  @DisplayName("없는 키워드 검색 시 빈 리스트 반환")
  void searchItems_noResult() {
    List<Item> result = itemRepository.searchItems("노트북");

    assertThat(result).isEmpty();
  }

  @Test
  @DisplayName("상점명으로 검색")
  void searchItems_byStoreName() {
    List<Item> result = itemRepository.searchItems("애플스토어");

    assertThat(result).hasSize(1)
        .extracting("itemName")
        .containsExactly("아이폰 15");
  }
}
