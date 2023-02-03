package org.example.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {
  @Test
  public void testIsBlank() {
    Assert.assertTrue(StringUtils.isBlank(null));
    Assert.assertTrue(StringUtils.isBlank(""));
    Assert.assertTrue(StringUtils.isBlank(" "));

    Assert.assertFalse(StringUtils.isBlank("foo"));
  }
}
