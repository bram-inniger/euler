package be.inniger.euler.problems01to10;

import java.util.stream.IntStream;

/**
 * Special Pythagorean triplet
 * <p>
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * a^2 + b^2 = c^2
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
public class Problem09 {

  private static final int TRIPLET_SUM = 1000;

  public int solve() {
    return IntStream.rangeClosed(1, TRIPLET_SUM)
        .boxed()
        .flatMap(a -> IntStream.rangeClosed(1, TRIPLET_SUM)
            .boxed()
            .map(b -> new PythagoreanTriplet(a, b, TRIPLET_SUM - a - b)))
        .filter(PythagoreanTriplet::isValid)
        .map(PythagoreanTriplet::getProduct)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

  private static class PythagoreanTriplet {

    private final int a;
    private final int b;
    private final int c;

    private PythagoreanTriplet(int a, int b, int c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }

    private boolean isValid() {
      return a > 0 &&
          b > 0 &&
          c > 0 &&
          a * a + b * b == c * c;
    }

    private int getProduct() {
      return a * b * c;
    }
  }
}
