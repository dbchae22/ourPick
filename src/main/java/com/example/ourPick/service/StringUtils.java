package com.example.ourPick.service;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {

  private static final int MAX_KEYWORD_LENGTH = 100;
  private static final int MIN_WORD_LENGTH = 3;

  public static String processSearchKeyword(String keyword) {
    if (keyword == null) {
      return "";
    }

    String normalized = keyword
        .trim()
        .replaceAll("[^0-9A-Za-z가-힣\\s]", "");

    if (normalized.isEmpty()) {
      return "";
    }

    if (normalized.length() > MAX_KEYWORD_LENGTH) {
      normalized = normalized.substring(0, MAX_KEYWORD_LENGTH);
    }

    String result = Arrays.stream(normalized.split("\\s+"))
        .filter(word -> word.length() >= MIN_WORD_LENGTH)
        .map(word -> "+" + word)
        .collect(Collectors.joining(" "));

    return result;
  }
}

