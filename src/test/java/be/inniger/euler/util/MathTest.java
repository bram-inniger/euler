package be.inniger.euler.util;

import org.junit.Test;

import java.util.NoSuchElementException;

import static be.inniger.euler.util.Math.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class MathTest {

  @Test
  public void canGetPrimes() {
    assertEquals(singletonList(2), getPrimesUpUntil(2));
    assertEquals(asList(2, 3, 5), getPrimesUpUntil(5));
    assertEquals(asList(2, 3, 5, 7, 11, 13, 17, 19), getPrimesUpUntil(19));
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Test(expected = IllegalArgumentException.class)
  public void throwsOnUpUntilNumberTooSmallToContainPrimes() {
    getPrimesUpUntil(1);
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Test(expected = IllegalArgumentException.class)
  public void throwsOnNegativeUpUntilNumber() {
    getPrimesUpUntil(-1);
  }

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

  @SuppressWarnings("ResultOfMethodCallIgnored")
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

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Test(expected = IllegalArgumentException.class)
  public void throwsOnTheUndefinedCaseWhereBothBaseandExponentAreZero() {
    pow(0, 0);
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Test(expected = IllegalArgumentException.class)
  public void throwsOnTheInvalidCaseWhereTheExponentIsNegativeAsTheResultCannotBeRepresentedByAnInteger() {
    pow(2, -1);
  }

  @Test
  public void canGetFactor() {
    assertEquals("Factor{prime=2, frequency=0}", getFactor(1, 2).toString());
    assertEquals("Factor{prime=2, frequency=1}", getFactor(2, 2).toString());
    assertEquals("Factor{prime=2, frequency=2}", getFactor(4, 2).toString());
    assertEquals("Factor{prime=2, frequency=1}", getFactor(6, 2).toString());
    assertEquals("Factor{prime=2, frequency=3}", getFactor(8, 2).toString());
    assertEquals("Factor{prime=2, frequency=2}", getFactor(12, 2).toString());
    assertEquals("Factor{prime=5, frequency=2}", getFactor(100, 5).toString());
  }

  @Test
  public void canMultiplyInts() {
    assertEquals(1, multiply(1, 1));
    assertEquals(-1, multiply(-1, 1));
    assertEquals(-1, multiply(1, -1));
    assertEquals(1, multiply(-1, -1));
    assertEquals(2, multiply(1, 2));
    assertEquals(2, multiply(2, 1));
    assertEquals(4, multiply(2, 2));
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Test(expected = ArithmeticException.class)
  public void throwsOnOverflowForInts() {
    multiply(Integer.MAX_VALUE, 2);
  }

  @Test
  public void canMultiplyLongs() {
    assertEquals(1L, multiply(1L, 1L));
    assertEquals(-1L, multiply(-1L, 1L));
    assertEquals(-1L, multiply(1L, -1L));
    assertEquals(1L, multiply(-1L, -1L));
    assertEquals(2L, multiply(1L, 2L));
    assertEquals(2L, multiply(2L, 1L));
    assertEquals(4L, multiply(2L, 2L));
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Test(expected = ArithmeticException.class)
  public void throwsOnOverflowForLongs() {
    multiply(Long.MAX_VALUE, 2L);
  }
}
