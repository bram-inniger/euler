package be.inniger.euler.util;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// TODO: Add tests and perhaps move its package

/**
 * Queryable collection of primes, backed by the sieve, will dynamically grow if more primes are requested
 */
public class UnboundPrimeSupplier implements PrimeSupplier {

  private int index = 0;

  private UnboundPrimeSupplier() {
  }

  public static UnboundPrimeSupplier newInstance() {
    return new UnboundPrimeSupplier();
  }

  @Override
  public int nextPrime() {
    return UnboundPrimeList.getInstance().getPrime(index++);
  }

  private enum UnboundPrimeList {
    INSTANCE;

    private static final int STARTING_UP_UNTIL_NR = 1000;

    private final Lock lock = new ReentrantLock();
    private int upUntilNr = STARTING_UP_UNTIL_NR;
    private List<Integer> primes = Math.getPrimesUpUntil(upUntilNr);

    private static UnboundPrimeList getInstance() {
      return INSTANCE;
    }

    private int getPrime(int index) {
      if (index >= primes.size()) {
        lock.lock();
        try {
          while (index >= upUntilNr) {
            upUntilNr *= 2;
          }

          primes = Math.getPrimesUpUntil(upUntilNr);
        } finally {
          lock.unlock();
        }
      }

      return primes.get(index);
    }
  }
}
