package be.inniger.euler.problems11to20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Problems11To20Test {

  @Test
  public void testSolve11() {
    assertEquals(70600674, new Problem11().solve());
  }

  @Test
  public void testSolve12() {
    assertEquals(76576500, new Problem12().solve());
  }

  @Test
  public void testSolve13() {
    assertEquals(5537376230L, new Problem13().solve());
  }

  @Test
  public void testSolve14() {
    assertEquals(837799L, new Problem14().solve());
  }

  @Test
  public void testSolve15() {
    assertEquals(137846528820L, new Problem15().solve());
  }
}
