package org.example.util;

import java.util.Optional;

public class StringUtils {

  public static boolean isBlank(String value) {
    return Optional.ofNullable(value).map(String::trim).map(String::isEmpty).orElse(true);
  }
}
