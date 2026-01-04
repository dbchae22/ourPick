package com.example.ourPick.service;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {

  private static final int MAX_KEYWORD_LENGTH = 100;

  public static String processSearchKeyword(String keyword) {
    String normalized = keyword.trim().replaceAll("\\s+", " ")
        .replaceAll("[+\\-<>()~*\"']", "");

    if (normalized.isEmpty()) {
      return "";
    }

    if (normalized.length() > MAX_KEYWORD_LENGTH) {
      normalized = normalized.substring(0, MAX_KEYWORD_LENGTH);
    }

    return Arrays.stream(normalized.split("\\s+"))
        .filter(word -> !word.isEmpty())
        .map(word -> "+" + word)
        .collect(Collectors.joining(" "));
  }
}

