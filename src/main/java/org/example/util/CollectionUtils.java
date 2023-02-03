package org.example.util;

import java.util.Collection;
import java.util.Optional;

public class CollectionUtils {

  public static boolean isBlank(Collection<String> value) {
    return Optional.ofNullable(value).map(Collection::isEmpty).orElse(true);
  }

  public static <E> boolean addIf(Collection<E> list, E item, boolean condition) {
    return condition ? list.add(item) : false;
  }

  public static boolean addIf(Collection<String> list, String value) {
    return addIf(list, value, !StringUtils.isBlank(value));
  }
}
