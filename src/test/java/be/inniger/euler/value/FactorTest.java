package be.inniger.euler.value;

import org.junit.Test;

import static be.inniger.euler.value.Factor.createFactor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FactorTest {

  @Test
  public void canCreateFactor() {
    assertEquals("Factor{prime=1, frequency=2}", createFactor(1, 2).toString());
  }

  @Test
  public void twoFactorsHaveEqualEquals() {
    assertEquals(createFactor(3, 4), createFactor(3, 4));
  }

  @Test
  public void twoFactorsHaveEqualHashCode() {
    assertEquals(createFactor(5, 6).hashCode(), createFactor(5, 6).hashCode());
  }

  @Test
  public void twoFactorsHaveEqualToString() {
    assertEquals(createFactor(7, 8).toString(), createFactor(7, 8).toString());
  }
  
  @Test
  public void differentFactorsAreNotEqual() {
    assertNotEquals(createFactor(9, 10), createFactor(10, 9));
  }
}
