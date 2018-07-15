package be.inniger.euler.util;

import java.util.List;
import java.util.stream.IntStream;

import static be.inniger.euler.util.Math.roundedSqrt;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Arrays.fill;
import static java.util.stream.Collectors.toUnmodifiableList;

public class EratosthenesSieve {

  private final List<Boolean> sieve;

  public EratosthenesSieve(int size) {
    if (size < 2) {
      throw new IllegalArgumentException(format("Size %d contains no primes! ", size));
    }

    this.sieve = calculateSieve(size);
  }

  public List<Integer> getPrimes() {
    return IntStream.range(0, sieve.size())
        .filter(sieve::get)
        .boxed()
        .collect(toUnmodifiableList());
  }

  private List<Boolean> calculateSieve(int size) {
    final var sieve = new Boolean[size + 1];
    fill(sieve, true);
    sieve[0] = false;
    sieve[1] = false;

    IntStream.rangeClosed(2, roundedSqrt(size))
        .filter(i -> sieve[i])
        .flatMap(i -> IntStream.iterate(i * i, j -> j < sieve.length, j -> j + i))
        .forEach(j -> sieve[j] = false);

    return asList(sieve);
  }
}
