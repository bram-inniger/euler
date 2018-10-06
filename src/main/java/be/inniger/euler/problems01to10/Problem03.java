package be.inniger.euler.problems01to10;

import be.inniger.euler.Problem;

import static be.inniger.euler.util.CollectionUtil.reverseStream;
import static be.inniger.euler.util.Math.getPrimesUpUntil;
import static be.inniger.euler.util.Math.roundedSqrt;

/*
 * Largest prime factor
 *
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */
public class Problem03 implements Problem {

  private static final long NUMBER = 600851475143L;

  @Override
  public long solve() {
    return reverseStream(getPrimesUpUntil(roundedSqrt(NUMBER)))
        .filter(prime -> NUMBER % prime == 0L)
        .findFirst()
        .orElseThrow();
  }
}
