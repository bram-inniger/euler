package be.inniger.euler.problems01to10;

import be.inniger.euler.util.EratosthenesSieve;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static be.inniger.euler.util.Math.pow;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;

/**
 * Smallest multiple
 * <p>
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class Problem05 {

  private static final int MAX_VALUE = 20;

  private final List<Integer> primes = new EratosthenesSieve(MAX_VALUE).getPrimes();

  public int solve() {
    return IntStream.rangeClosed(1, MAX_VALUE) // Iterate over all numbers from 1 to 20
        .boxed()
        .flatMap(number -> primes.stream()
            .map(prime -> new Factor(prime, getFrequency(number, prime))) // Decompose every number into its Factors
            .filter(factor -> factor.frequency > 0)) // Remove all the Factors that never occur (frequency = 0)
        .collect(groupingBy(Factor::getPrime))// Group all these Factors by prime
        .values()
        .stream()
        .map(factorsPerPrime -> factorsPerPrime.stream()
            .max(comparing(Factor::getFrequency))) // Per prime pick the factor with the highest frequency
        .flatMap(Optional::stream)
        .map(factor -> pow(factor.prime, factor.frequency)) // Get the power of each prime factor back with its frequency
        .reduce(1, java.lang.Math::multiplyExact); // Make the product of all these powers to get the smallest number evenly divisible by all numbers
  }

  /**
   * Calculate how many times a given prime "fits" in a number.
   * Start by checking if it fits once, twice, thrice, ...
   * The first time "nr times it fits + 1" does not fit any more, then the answer is found!
   */
  private int getFrequency(int number, int prime) {
    return IntStream.iterate(1, i -> i + 1)
        .dropWhile(i -> number % pow(prime, i + 1) == 0)
        .findFirst()
        .orElseThrow();
  }

  /**
   * Store which prime occurred how many times in a number.
   */
  private static class Factor {

    private final int prime;
    private final int frequency;

    private Factor(int prime, int frequency) {
      this.prime = prime;
      this.frequency = frequency;
    }

    private int getPrime() {
      return prime;
    }

    public int getFrequency() {
      return frequency;
    }
  }
}
