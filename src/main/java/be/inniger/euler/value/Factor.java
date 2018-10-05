package be.inniger.euler.value;

import be.inniger.euler.util.Math;

import java.util.Objects;

/*
 * Abstraction used to represent a factor from a factorized number.
 * E.g. 8 factorized yields a factor from prime value 2 occurring 3 times (exponent)
 */
public final class Factor {

  private final int prime;
  private final int exponent;

  private Factor(int prime, int exponent) {
    this.prime = prime;
    this.exponent = exponent;
  }

  public static Factor factor(int prime, int exponent) {
    return new Factor(prime, exponent);
  }

  public int getPrime() {
    return prime;
  }

  public int getExponent() {
    return exponent;
  }

  /*
   * Returns the total value this one factor contributes to the number
   */
  public int getValue() {
    return Math.pow(prime, exponent);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Factor factor = (Factor) o;
    return prime == factor.prime &&
        exponent == factor.exponent;
  }

  @Override
  public int hashCode() {
    return Objects.hash(prime, exponent);
  }

  @Override
  public String toString() {
    return "Factor{" +
        "prime=" + prime +
        ", exponent=" + exponent +
        '}';
  }
}
