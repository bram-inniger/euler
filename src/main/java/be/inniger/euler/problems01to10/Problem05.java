package be.inniger.euler.problems01to10;

import be.inniger.euler.util.Math;
import be.inniger.euler.value.Factor;

import java.util.Optional;
import java.util.stream.IntStream;

import static be.inniger.euler.util.Math.getFactor;
import static be.inniger.euler.util.Math.getPrimesUpUntil;
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

  public int solve() {
    var primes = getPrimesUpUntil(MAX_VALUE);

    return IntStream.rangeClosed(1, MAX_VALUE) // Iterate over all numbers from 1 to 20
        .boxed()
        .flatMap(number -> primes.stream()
            .map(prime -> getFactor(number, prime)) // Decompose every number into its Factors
            .filter(factor -> factor.getFrequency() > 0)) // Remove all the Factors that never occur (frequency = 0)
        .collect(groupingBy(Factor::getPrime))// Group all these Factors by prime
        .values()
        .stream()
        .map(factorsPerPrime -> factorsPerPrime.stream()
            .max(comparing(Factor::getFrequency))) // Per prime pick the factor with the highest frequency
        .flatMap(Optional::stream)
        .map(factor -> pow(factor.getPrime(), factor.getFrequency())) // Get the power of each prime factor back with its frequency
        .reduce(1, Math::multiply); // Make the product of all these powers to get the smallest number evenly divisible by all numbers
  }
}
