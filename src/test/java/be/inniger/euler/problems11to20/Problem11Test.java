package be.inniger.euler.problems11to20;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Problem11Test {

  @Test
  public void testSolve() {
    int expected = 70600674;
    int actual = new Problem11().solve();

    assertEquals(expected, actual);
  }
}
