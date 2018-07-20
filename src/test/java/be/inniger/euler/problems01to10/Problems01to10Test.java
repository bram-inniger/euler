package be.inniger.euler.problems01to10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Problems01to10Test {

  @Test
  public void testSolve01() {
    assertEquals(233168, new Problem01().solve());
  }

  @Test
  public void testSolve02() {
    assertEquals(4613732, new Problem02().solve());
  }

  @Test
  public void testSolve03() {
    assertEquals(6857, new Problem03().solve());
  }

  @Test
  public void testSolve04() {
    assertEquals(906609, new Problem04().solve());
  }

  @Test
  public void testSolve05() {
    assertEquals(232792560, new Problem05().solve());
  }

  @Test
  public void testSolve06() {
    assertEquals(25164150, new Problem06().solve());
  }

  @Test
  public void testSolve07() {
    assertEquals(104743, new Problem07().solve());
  }

  @Test
  public void testSolve08() {
    assertEquals(23514624000L, new Problem08().solve());
  }

  @Test
  public void testSolve09() {
    assertEquals(31875000, new Problem09().solve());
  }

  @Test
  public void testSolve10() {
    assertEquals(142913828922L, new Problem10().solve());
  }
}
