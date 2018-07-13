package be.inniger.euler.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static be.inniger.euler.util.Math.roundedSqrt;
import static java.lang.Math.floor;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Arrays.fill;
import static java.util.stream.Collectors.toList;

public class EratosthenesSieve {

  private final List<Boolean> sieve;

  public EratosthenesSieve(int size) {
    this.sieve = Optional.of(size)
        .filter(__ -> size >= 2)
        .map(this::calculateSieve)
        .orElseThrow(() -> new IllegalArgumentException(format("Size %d contains no primes! ", size)));
  }

  public List<Integer> getPrimes() {
    return IntStream.range(0, sieve.size())
        .boxed()
        .filter(sieve::get)
        .collect(toList());
  }

  private List<Boolean> calculateSieve(int size) {
    final Boolean[] sieve = new Boolean[size + 1];
    fill(sieve, true);
    sieve[0] = false;
    sieve[1] = false;

    IntStream.rangeClosed(2, roundedSqrt(size))
        .filter(i -> sieve[i])
        .flatMap(i -> steppedRangeClosed(i * i, sieve.length - 1, i))
        .forEach(j -> sieve[j] = false);

    return asList(sieve);
  }

  private IntStream steppedRangeClosed(int startInclusive, int endInclusive, int stepSize) {
    long nrSteps = (long) floor((double) (endInclusive - startInclusive) / (double) stepSize + (double) 1);

    return IntStream.iterate(startInclusive, i -> i + stepSize).limit(nrSteps);
  }
}
