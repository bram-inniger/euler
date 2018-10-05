package be.inniger.euler.value;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static be.inniger.euler.value.Factor.factor;
import static be.inniger.euler.value.FactorizedInteger.factorizedInteger;
import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FactorizedIntegerTest {

  @Test
  public void canCreateFactorizedInteger() {
    assertEquals("FactorizedInteger{value=8, factors={2=Factor{prime=2, exponent=3}}}",
        factorizedInteger(8).toString());
  }

  @Test
  public void twoFactorizedIntegersHaveEqualEquals() {
    assertEquals(factorizedInteger(100), factorizedInteger(100));
  }

  @Test
  public void twoFactorizedIntegersHaveEqualHashCode() {
    assertEquals(factorizedInteger(42).hashCode(), factorizedInteger(42).hashCode());
  }

  @Test
  public void twoFactorizedIntegersHaveEqualToString() {
    assertEquals(factorizedInteger(25).toString(), factorizedInteger(25).toString());
  }

  @Test
  public void differentFactorizedIntegersAreNotEqual() {
    assertNotEquals(factorizedInteger(2), factorizedInteger(3));
  }

  @Test
  public void canGetFactors() {
    assertEquals(Set.of(), getFactors(1));
    assertEquals(Set.of(factor(2, 1)), getFactors(2));
    assertEquals(Set.of(factor(2, 2)), getFactors(4));
    assertEquals(Set.of(factor(5, 1)), getFactors(5));
    assertEquals(Set.of(factor(2, 1), factor(3, 1)), getFactors(6));
    assertEquals(Set.of(factor(2, 3)), getFactors(8));
    assertEquals(Set.of(factor(2, 2), factor(3, 1)), getFactors(12));
    assertEquals(Set.of(factor(2, 2), factor(5, 2)), getFactors(100));
  }

  private Set<Factor> getFactors(int number) {
    return factorizedInteger(number).getFactors().collect(toSet());
  }
}
