package be.inniger.euler.util;

import org.junit.Test;

import static be.inniger.euler.util.Math.roundedSqrt;
import static org.junit.Assert.assertEquals;

public class MathTest {

  @Test
  public void canCalculateTheSquareRoot() {
    assertEquals(0, roundedSqrt(0L));
    assertEquals(1, roundedSqrt(1L));
    assertEquals(1, roundedSqrt(2L));
    assertEquals(2, roundedSqrt(3L));
    assertEquals(2, roundedSqrt(4L));
    assertEquals(2, roundedSqrt(4));
    assertEquals(2, roundedSqrt(4.0));
    assertEquals(2, roundedSqrt(4.0f));
    assertEquals(12, roundedSqrt(143L));
    assertEquals(12, roundedSqrt(144L));
    assertEquals(12, roundedSqrt(145L));
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwsOnTheSquareRootOfNegativeNumbersAsTheResultCannotBeRepresentedByAnInteger() {
    roundedSqrt(-1);
  }
}
