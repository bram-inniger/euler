package be.inniger.euler.problems01to10;

import static be.inniger.euler.util.Math.getPrimesUpUntil;
import static be.inniger.euler.util.Math.roundedSqrt;
import static be.inniger.euler.util.StreamUtil.reverseStream;

/**
 * Largest prime factor
 * <p>
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */
public class Problem03 {

  private static final long NUMBER = 600851475143L;

  public int solve() {
    return reverseStream(getPrimesUpUntil(roundedSqrt(NUMBER)))
        .filter(prime -> NUMBER % prime == 0L)
        .findFirst()
        .orElseThrow();
  }
}
