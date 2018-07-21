package be.inniger.euler.util;

import be.inniger.euler.value.Factor;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static be.inniger.euler.value.Factor.createFactor;
import static java.lang.Double.isNaN;
import static java.lang.String.format;
import static java.util.Arrays.fill;
import static java.util.stream.Collectors.toUnmodifiableList;

public final class Math {

  public static final Double E = java.lang.Math.E;

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

    if (exponent < 0) {
      throw new IllegalArgumentException("Negative exponents lead to results that cannot be assigned to Int");
    }

    return IntStream.generate(() -> base)
        .limit(exponent)
        .reduce(1, Math::multiply);
  }

  public static Factor getFactor(int number, int prime) {
    // Calculate how many times a given prime "fits" in a number, 
    // the first time "nr times it fits + 1" is no fit, the answer is found!
    var frequency = prime > number ?
        0 :
        IntStream.iterate(1, Math::inc)
            .dropWhile(i -> number % pow(prime, i + 1) == 0)
            .findFirst()
            .orElseThrow();
    return createFactor(prime, frequency);
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

  public static boolean isStrictlyPositive(long number) {
    return number >= 0L;
  }

  public static boolean isPositive(long number) {
    return number > 0L;
  }
}
