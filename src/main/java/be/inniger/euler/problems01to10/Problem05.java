package be.inniger.euler.problems01to10;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Math;
import be.inniger.euler.value.Factor;
import be.inniger.euler.value.FactorizedInteger;

import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;

/**
 * Smallest multiple
 * <p>
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class Problem05 implements Problem {

  private static final int MAX_VALUE = 20;

  @Override
  public long solve() {
    return IntStream.rangeClosed(1, MAX_VALUE) // Iterate over all numbers from 1 to 20
        .mapToObj(FactorizedInteger::factorizedInteger) // Decompose every number into its Factors
        .flatMap(FactorizedInteger::getFactors) // Stream all these Factors
        .collect(groupingBy(Factor::getPrime)) // Group all these Factors by prime
        .values()
        .stream()
        .map(factorsPerPrime -> factorsPerPrime.stream()
            .max(comparing(Factor::getExponent))) // Per prime pick the factor with the highest frequency
        .flatMap(Optional::stream)
        .map(Factor::getValue) // Get the power of each prime factor back with its frequency
        .reduce(1, Math::multiply); // Make the product of all these powers to get the smallest number evenly divisible by all numbers
  }
}
