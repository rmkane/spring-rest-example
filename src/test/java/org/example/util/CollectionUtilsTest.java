package org.example.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class CollectionUtilsTest {
  @Test
  public void testAddIf() {
    List<String> items = new ArrayList<>();

    CollectionUtils.addIf(items, "foo", false);
    CollectionUtils.addIf(items, "bar", true);
    CollectionUtils.addIf(items, "");

    Assert.assertEquals(Collections.singletonList("bar"), items);
  }
}
