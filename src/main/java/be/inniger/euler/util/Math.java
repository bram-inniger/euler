package be.inniger.euler.util;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.lang.Double.isNaN;
import static java.lang.String.format;
import static java.util.Arrays.fill;
import static java.util.stream.Collectors.toUnmodifiableList;

public final class Math {

  private Math() {
    throw new IllegalStateException("Utility class constructor should never be called!");
  }

  public static List<Integer> getPrimesUpUntil(int number) {
    if (number < 2) {
      throw new IllegalArgumentException(format("Size %d contains no primes! ", number));
    }

    final var sieve = new Boolean[number + 1];
    fill(sieve, true);
    sieve[0] = false;
    sieve[1] = false;

    IntStream.rangeClosed(2, roundedSqrt(number))
        .filter(i -> sieve[i])
        .flatMap(i -> IntStream.iterate(i * i, j -> j < sieve.length, j -> j + i))
        .forEach(j -> sieve[j] = false);

    return IntStream.range(0, sieve.length)
        .filter(i -> sieve[i])
        .boxed()
        .collect(toUnmodifiableList());
  }

  public static int roundedSqrt(Number number) {
    return Optional.ofNullable(number)
        .map(Number::doubleValue)
        .map(java.lang.Math::sqrt)
        .filter(root -> !isNaN(root))
        .map(java.lang.Math::round)
        .map(Long::intValue)
        .orElseThrow();
  }

  public static int pow(int base, int exponent) {
    if (base == 0 && exponent == 0) {
      throw new IllegalArgumentException("Base and exponent cannot both be 0, this is undefined");
    }

    if (isNegative(exponent)) {
      throw new IllegalArgumentException("Negative exponents lead to results that cannot be assigned to Int");
    }

    return IntStream.generate(() -> base)
        .limit(exponent)
        .reduce(1, Math::multiply);
  }

  public static BigInteger factorial(int number) {
    if (isNegative(number)) {
      throw new IllegalArgumentException(format("Cannot calculate the factorial of a negative number: %d", number));
    }

    // By convention
    if (number == 0 || number == 1) {
      return BigInteger.ONE;
    }

    return IntStream.iterate(number, Math::isPositive, Math::dec)
        .mapToObj(BigInteger::valueOf)
        .reduce(BigInteger::multiply)
        .orElseThrow();
  }

  /*
   * Proxy methods to official JDK methods or trivial self-written methods
   * Testing is unneeded for any of these
   */

  public static int sum(int x, int y) {
    return Integer.sum(x, y);
  }

  public static long sum(long x, long y) {
    return Long.sum(x, y);
  }

  public static int multiply(int x, int y) {
    return java.lang.Math.multiplyExact(x, y);
  }

  public static long multiply(long x, long y) {
    return java.lang.Math.multiplyExact(x, y);
  }

  public static int square(int number) {
    return java.lang.Math.multiplyExact(number, number);
  }

  public static long square(long number) {
    return java.lang.Math.multiplyExact(number, number);
  }

  public static double log(double number) {
    return java.lang.Math.log(number);
  }

  public static int toInt(Number number) {
    return number.intValue();
  }

  public static long toLong(Number number) {
    return number.longValue();
  }

  public static int inc(int number) {
    return java.lang.Math.incrementExact(number);
  }

  public static long inc(long number) {
    return java.lang.Math.incrementExact(number);
  }

  public static int dec(int number) {
    return java.lang.Math.decrementExact(number);
  }

  public static long dec(long number) {
    return java.lang.Math.decrementExact(number);
  }

  public static boolean isStrictlyPositive(int number) {
    return number >= 0;
  }

  public static boolean isPositive(int number) {
    return number > 0;
  }

  public static boolean isNegative(int number) {
    return !isStrictlyPositive(number);
  }

  public static boolean isStrictlyPositive(long number) {
    return number >= 0L;
  }

  public static boolean isPositive(long number) {
    return number > 0L;
  }

  public static boolean isNegative(long number) {
    return !isStrictlyPositive(number);
  }

  public static int abs(int number) {
    return java.lang.Math.abs(number);
  }

  public static long abs(long number) {
    return java.lang.Math.abs(number);
  }

  public static boolean isEven(int number) {
    return number % 2 == 0;
  }

  public static boolean isEven(long number) {
    return number % 2 == 0;
  }

  public static boolean isOdd(int number) {
    return !isEven(number);
  }

  public static boolean isOdd(long number) {
    return !isEven(number);
  }
}
