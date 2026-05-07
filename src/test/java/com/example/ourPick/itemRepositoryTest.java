package com.example.ourPick;

import com.example.ourPick.domain.Item;
import com.example.ourPick.repository.ItemRepository;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemRepositoryTest {

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @BeforeAll
  void setUp() {
    try {
      jdbcTemplate.execute("ALTER TABLE items DROP INDEX idx_fulltext");
    } catch (Exception ignored) {}
    jdbcTemplate.execute("ALTER TABLE items ADD FULLTEXT INDEX idx_fulltext (item_name, store_name)");

    itemRepository.save(new Item("아이폰 15", "애플스토어", 10, 10000, "mainPhoto1"));
    itemRepository.save(new Item("갤럭시 S24", "삼성스토어", 20, 20000, "mainPhoto2"));
    itemRepository.save(new Item("아이폰 케이스", "쿠팡", 30, 30000, "mainPhoto3"));
  }

  @AfterAll
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
