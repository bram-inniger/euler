package be.inniger.euler.util;

import java.util.Iterator;
import java.util.List;

// TODO move to a more suitable package
/**
 * Queryable collection of primes, backed by the sieve, will dynamically grow if more primes are requested
 */
public class PrimeCollection implements Iterable<Integer> {

  private static final int STARTING_UP_UNTIL_NR = 1000;

  private int upUntilNr = STARTING_UP_UNTIL_NR;
  private List<Integer> primes = Math.getPrimesUpUntil(upUntilNr);

  public int getPrime(int index) {
    while (index >= primes.size()) {
      primes = Math.getPrimesUpUntil(upUntilNr *= 2);
    }

    return primes.get(index);
  }

  @Override
  public Iterator<Integer> iterator() {
    return new Iterator<>() {

      int index = 0;

      @Override
      public boolean hasNext() {
        return true;
      }

      @Override
      public Integer next() {
        return getPrime(index++);
      }
    };
  }
}
