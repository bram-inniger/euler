package be.inniger.euler.value;

import org.junit.jupiter.api.Test;

import static be.inniger.euler.value.Factor.factor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FactorTest {

  @Test
  public void canCreateFactor() {
    assertEquals("Factor{prime=1, exponent=2}", factor(1, 2).toString());
  }

  @Test
  public void twoFactorsHaveEqualEquals() {
    assertEquals(factor(3, 4), factor(3, 4));
  }

  @Test
  public void twoFactorsHaveEqualHashCode() {
    assertEquals(factor(5, 6).hashCode(), factor(5, 6).hashCode());
  }

  @Test
  public void twoFactorsHaveEqualToString() {
    assertEquals(factor(7, 8).toString(), factor(7, 8).toString());
  }

  @Test
  public void differentFactorsAreNotEqual() {
    assertNotEquals(factor(9, 10), factor(10, 9));
  }

  @Test
  public void canGetValue() {
    assertEquals(2, factor(2, 1).getValue());
    assertEquals(4, factor(2, 2).getValue());
    assertEquals(8, factor(2, 3).getValue());
    assertEquals(125, factor(5, 3).getValue());
  }
}
