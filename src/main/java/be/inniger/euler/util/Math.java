package be.inniger.euler.util;

import java.util.Optional;

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
        .orElseThrow(IllegalArgumentException::new);
  }
}
