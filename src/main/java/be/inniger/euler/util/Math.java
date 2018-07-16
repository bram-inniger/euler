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
        IntStream.iterate(1, i -> i + 1)
            .dropWhile(i -> number % pow(prime, i + 1) == 0)
            .findFirst()
            .orElseThrow();
    return createFactor(prime, frequency);
  }

  public static int multiply(int x, int y) {
    return java.lang.Math.multiplyExact(x, y);
  }

  public static long multiply(long x, long y) {
    return java.lang.Math.multiplyExact(x, y);
  }
}
