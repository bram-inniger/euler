package be.inniger.euler.problems01to10;

import be.inniger.euler.util.EratosthenesSieve;

import java.util.stream.DoubleStream;

/**
 * 10001st prime
 * <p>
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * What is the 10 001st prime number?
 */
public class Problem07 {

  // -1 as the problem specification is 1-indexed but Java's collections are 0-indexed
  private static final int PRIME_INDEX = 10_001 - 1;

  public int solve() {
    return new EratosthenesSieve(getSieveSize()).getPrimes().get(PRIME_INDEX);
  }

  /**
   * Calculate a reasonable upper bound to use as sieve size, in a way that the sieve will contain the 10_001th prime.
   * The code below very roughly calculates the inverse of a Prime-counting function.
   * Details here: https://en.wikipedia.org/wiki/Prime-counting_function
   * <p>
   * The approximate function to count primes "pi(N) = N / ln(N)" is reversed by trying exponentially larger N values,
   * until "pi(N)" evaluates to something larger than 10_001.
   * This way we know that calculating the sieve up until N should contain the 10_001th prime.
   * <p>
   * When running the program for the 10_001th prime we find the following values:
   * * Prime nr. 10_001 = 104743
   * * Upper sieve size = 125278
   * Showing that indeed the upper bound is reasonably guessed!
   */
  private int getSieveSize() {
    return (int) DoubleStream.iterate(10.0, n -> n * 1.1)
        .filter(n -> n / java.lang.Math.log(n) > PRIME_INDEX)
        .findFirst()
        .orElseThrow();
  }
}
