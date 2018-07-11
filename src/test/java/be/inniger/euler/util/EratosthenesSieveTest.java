package be.inniger.euler.util;

import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class EratosthenesSieveTest {

  @Test(expected = IllegalArgumentException.class)
  public void throwsOnSieveSizesTooSmallToContainPrimes() {
    new EratosthenesSieve(1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwsOnNegativeSieveSize() {
    new EratosthenesSieve(-1);
  }

  @Test
  public void canGetPrimes() {
    assertEquals(singletonList(2), new EratosthenesSieve(2).getPrimes());
    assertEquals(asList(2, 3, 5), new EratosthenesSieve(5).getPrimes());
    assertEquals(asList(2, 3, 5, 7, 11, 13, 17, 19), new EratosthenesSieve(19).getPrimes());
  }
}
