package com.example.ourPick;

import com.example.ourPick.service.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {

  @Test
  @DisplayName("단일_단어_검색어를_처리한다")
  void processSearchKeyword_ShouldProcessSingleWord() {
    String result = StringUtils.processSearchKeyword("스웨터");

    Assertions.assertEquals("+스웨터", result);
  }

  @Test
  @DisplayName("여러_단어_검색어를_처리한다")
  void processSearchKeyword_ShouldProcessMultipleWords() {
    String result = StringUtils.processSearchKeyword("스웨터 니트");

    Assertions.assertEquals("+스웨터 +니트", result);
  }

  @Test
  @DisplayName("공백이_많은_검색어를_정규화한다")
  void processSearchKeyword_ShouldNormalizeWhitespace() {
    String result = StringUtils.processSearchKeyword("  스웨터    니트  ");

    Assertions.assertEquals("+스웨터 +니트", result);
  }

  @Test
  @DisplayName("특수문자를_제거한다")
  void processSearchKeyword_ShouldRemoveSpecialCharacters() {
    String result = StringUtils.processSearchKeyword("스웨터+니트-가을");

    Assertions.assertEquals("+스웨터 +니트 +가을", result);
  }

  @Test
  @DisplayName("모든_BOOLEAN_MODE_특수문자를_제거한다")
  void processSearchKeyword_ShouldRemoveAllBooleanModeSpecialCharacters() {
    String result = StringUtils.processSearchKeyword("스웨터+-<>()~*\"'니트");

    Assertions.assertEquals("+스웨터 +니트", result);
  }

  @Test
  @DisplayName("검색어_길이가_100자를_초과하면_100자로_제한한다")
  void processSearchKeyword_ShouldLimitToMaxLength() {
    String longKeyword = "가".repeat(150);
    String result = StringUtils.processSearchKeyword(longKeyword);

    Assertions.assertEquals(100, result.replaceAll("\\+", "").replaceAll(" ", "").length());
    Assertions.assertTrue(result.startsWith("+"));
  }

  @Test
  @DisplayName("빈_문자열은_빈_문자열을_반환한다")
  void processSearchKeyword_ShouldReturnEmptyString_WhenInputIsEmpty() {
    String result = StringUtils.processSearchKeyword("");

    Assertions.assertEquals("", result);
  }

  @Test
  @DisplayName("공백만_있는_문자열은_빈_문자열을_반환한다")
  void processSearchKeyword_ShouldReturnEmptyString_WhenInputIsOnlyWhitespace() {
    String result = StringUtils.processSearchKeyword("   ");

    Assertions.assertEquals("", result);
  }

  @Test
  @DisplayName("특수문자만_있는_문자열은_빈_문자열을_반환한다")
  void processSearchKeyword_ShouldReturnEmptyString_WhenInputIsOnlySpecialCharacters() {
    String result = StringUtils.processSearchKeyword("+-<>()~*\"'");

    Assertions.assertEquals("", result);
  }

  @Test
  @DisplayName("연속된_특수문자와_공백을_처리한다")
  void processSearchKeyword_ShouldHandleConsecutiveSpecialCharactersAndSpaces() {
    String result = StringUtils.processSearchKeyword("스웨터+++---니트   가을");

    Assertions.assertEquals("+스웨터 +니트 +가을", result);
  }

  @Test
  @DisplayName("영문과_숫자가_포함된_검색어를_처리한다")
  void processSearchKeyword_ShouldHandleEnglishAndNumbers() {
    String result = StringUtils.processSearchKeyword("sweater 123 니트");

    Assertions.assertEquals("+sweater +123 +니트", result);
  }

  @Test
  @DisplayName("100자_정확히_들어가는_경우_정상_처리한다")
  void processSearchKeyword_ShouldProcessExactly100Characters() {
    String keyword = "가".repeat(100);
    String result = StringUtils.processSearchKeyword(keyword);

    Assertions.assertEquals("+" + keyword, result);
  }

}

