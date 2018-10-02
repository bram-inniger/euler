package be.inniger.euler.value;

import be.inniger.euler.util.Math;

import java.util.Objects;

/**
 * TODO add better description
 * Store which prime occurred how many times in a number.
 */
public final class Factor {

  private final int prime;
  private final int exponent;

  private Factor(int prime, int exponent) {
    this.prime = prime;
    this.exponent = exponent;
  }

  public static Factor factor(int prime, int frequency) {
    return new Factor(prime, frequency);
  }

  public int getPrime() {
    return prime;
  }

  public int getExponent() {
    return exponent;
  }

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
