package be.inniger.euler.util;

import java.util.List;
import java.util.stream.IntStream;

import static be.inniger.euler.util.Math.roundedSqrt;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Arrays.fill;
import static java.util.stream.Collectors.toList;

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
        .boxed()
        .filter(sieve::get)
        .collect(toList());
  }

  private List<Boolean> calculateSieve(int size) {
    final Boolean[] sieve = new Boolean[size + 1];
    fill(sieve, true);
    sieve[0] = false;
    sieve[1] = false;

    for (int i = 2; i <= roundedSqrt(size); i++) {
      if (sieve[i]) {
        for (int j = i * i; j < sieve.length - 1; j += i) {
          sieve[j] = false;
        }
      }
    }

    return asList(sieve);
  }
}
