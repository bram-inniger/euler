package be.inniger.euler.util;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;

import static be.inniger.euler.util.Math.factorial;
import static be.inniger.euler.util.Math.getFactor;
import static be.inniger.euler.util.Math.getPrimesUpUntil;
import static be.inniger.euler.util.Math.pow;
import static be.inniger.euler.util.Math.roundedSqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathTest {

  @Test
  public void canGetPrimes() {
    assertEquals(List.of(2), getPrimesUpUntil(2));
    assertEquals(List.of(2, 3, 5), getPrimesUpUntil(5));
    assertEquals(List.of(2, 3, 5, 7, 11, 13, 17, 19), getPrimesUpUntil(19));
  }

  @Test
  public void throwsOnUpUntilNumberTooSmallToContainPrimes() {
    assertThrows(IllegalArgumentException.class,
        () -> getPrimesUpUntil(1));
  }

  @Test
  public void throwsOnNegativeUpUntilNumber() {
    assertThrows(IllegalArgumentException.class,
        () -> getPrimesUpUntil(-1));
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

  @Test
  public void throwsOnTheSquareRootOfNegativeNumbersAsTheResultCannotBeRepresentedByAnInteger() {
    assertThrows(NoSuchElementException.class,
        () -> roundedSqrt(-1));
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

  @Test
  public void throwsOnTheUndefinedCaseWhereBothBaseAndExponentAreZero() {
    assertThrows(IllegalArgumentException.class,
        () -> pow(0, 0));
  }

  @Test
  public void throwsOnTheInvalidCaseWhereTheExponentIsNegativeAsTheResultCannotBeRepresentedByAnInteger() {
    assertThrows(IllegalArgumentException.class,
        () -> pow(2, -1));
  }

  @Test
  public void canGetFactor() {
    assertEquals("Factor{prime=2, frequency=0}", getFactor(1, 2).toString());
    assertEquals("Factor{prime=2, frequency=1}", getFactor(2, 2).toString());
    assertEquals("Factor{prime=2, frequency=2}", getFactor(4, 2).toString());
    assertEquals("Factor{prime=2, frequency=0}", getFactor(5, 2).toString());
    assertEquals("Factor{prime=2, frequency=1}", getFactor(6, 2).toString());
    assertEquals("Factor{prime=2, frequency=3}", getFactor(8, 2).toString());
    assertEquals("Factor{prime=2, frequency=2}", getFactor(12, 2).toString());
    assertEquals("Factor{prime=5, frequency=2}", getFactor(100, 5).toString());
  }

  @Test
  public void canCalculateFactorial() {
    assertEquals(new BigInteger("1"), factorial(0));
    assertEquals(new BigInteger("1"), factorial(1));
    assertEquals(new BigInteger("2"), factorial(2));
    assertEquals(new BigInteger("6"), factorial(3));

    // Verified by https://www.wolframalpha.com/input/?i=100!
    assertEquals(new BigInteger("93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000"), factorial(100));
  }

  @Test
  public void throwsOnTheInvalidCaseWhereTheNegativeFactorialIsCalculated() {
    assertThrows(IllegalArgumentException.class,
        () -> factorial(-1));
  }
}
