package be.inniger.euler.util;

import java.util.Optional;
import java.util.stream.IntStream;

import static java.lang.Double.isNaN;

public class Math {

  private Math() {
    throw new IllegalStateException("Utility class constructor should never be called!");
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
        .reduce(1, java.lang.Math::multiplyExact);
  }
}
