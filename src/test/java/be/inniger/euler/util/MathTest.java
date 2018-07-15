package be.inniger.euler.util;

import org.junit.Test;

import java.util.NoSuchElementException;

import static be.inniger.euler.util.Math.pow;
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

  @Test(expected = NoSuchElementException.class)
  public void throwsOnTheSquareRootOfNegativeNumbersAsTheResultCannotBeRepresentedByAnInteger() {
    roundedSqrt(-1);
  }

  @Test
  public void canCalculateThePowerOfAPositiveIntegerBaseWithAPositiveIntegerExponent() {
    assertEquals(0, pow(0, 1));
    assertEquals(0, pow(0, 2));
    assertEquals(1, pow(1, 0));
    assertEquals(1, pow(1, 1));
    assertEquals(1, pow(1, 2));
    assertEquals(1, pow(2, 0));
    assertEquals(2, pow(2, 1));
    assertEquals(4, pow(2, 2));
    assertEquals(8, pow(2, 3));
  }

  @Test
  public void canCalculateThePowerOfANegativeIntegerBaseWithAPositiveIntegerExponent() {
    assertEquals(-2, pow(-2, 1));
    assertEquals(4, pow(-2, 2));
    assertEquals(-8, pow(-2, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwsOnTheUndefinedCaseWhereBothBaseandExponentAreZero() {
    pow(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwsOnTheInvalidCaseWhereTheExponentIsNegativeAsTheResultCannotBeRepresentedByAnInteger() {
    pow(2, -1);
  }
}
