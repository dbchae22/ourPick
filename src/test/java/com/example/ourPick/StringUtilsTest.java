package com.example.ourPick;

import com.example.ourPick.service.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {

  @Test
  @DisplayName("null이면 빈 문자열 반환")
  void returnsEmpty_whenNull() {
    Assertions.assertEquals("", StringUtils.processSearchKeyword(null));
  }

  @Test
  @DisplayName("공백만 있으면 빈 문자열 반환")
  void returnsEmpty_whenBlankOnly() {
    Assertions.assertEquals("", StringUtils.processSearchKeyword(""));
    Assertions.assertEquals("", StringUtils.processSearchKeyword("   "));
    Assertions.assertEquals("", StringUtils.processSearchKeyword("\n\t  "));
  }

  @Test
  @DisplayName("특수문자만 있으면 빈 문자열 반환")
  void returnsEmpty_whenOnlySpecialChars() {
    Assertions.assertEquals("", StringUtils.processSearchKeyword("!!!@@@###"));
    Assertions.assertEquals("", StringUtils.processSearchKeyword("!!!   @@@"));
    Assertions.assertEquals("", StringUtils.processSearchKeyword("+-<>()~*\"'"));
  }


  @Test
  @DisplayName("3글자 미만 검색어는 제거된다")
  void filtersOutShortTexts() {
    Assertions.assertEquals("", StringUtils.processSearchKeyword("a ab 가 가방"));
    Assertions.assertEquals("+abc", StringUtils.processSearchKeyword("a ab abc"));
    Assertions.assertEquals("+나이키", StringUtils.processSearchKeyword("나이키 신발"));
  }


  @Test
  @DisplayName("검색어_길이가_100자를_초과하면_100자로_제한한다")
  void truncatesToMaxLength() {
    String longKeyword = "가".repeat(150);
    String result = StringUtils.processSearchKeyword(longKeyword);

    Assertions.assertEquals(101, result.length());
  }

  @Test
  @DisplayName("100자_정확히_들어가는_경우_정상_처리한다")
  void doesNotTruncate_whenLengthEqualsMax() {
    String keyword = "가".repeat(100);
    String result = StringUtils.processSearchKeyword(keyword);

    Assertions.assertEquals("+" + keyword, result);
  }

}

