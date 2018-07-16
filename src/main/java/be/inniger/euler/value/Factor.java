package be.inniger.euler.value;

import java.util.Objects;

/**
 * Store which prime occurred how many times in a number.
 */
public final class Factor {

  private final int prime;
  private final int frequency;

  private Factor(int prime, int frequency) {
    this.prime = prime;
    this.frequency = frequency;
  }

  public static Factor createFactor(int prime, int frequency) {
    return new Factor(prime, frequency);
  }

  public int getPrime() {
    return prime;
  }

  public int getFrequency() {
    return frequency;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Factor factor = (Factor) o;
    return prime == factor.prime &&
        frequency == factor.frequency;
  }

  @Override
  public int hashCode() {
    return Objects.hash(prime, frequency);
  }

  @Override
  public String toString() {
    return "Factor{" +
        "prime=" + prime +
        ", frequency=" + frequency +
        '}';
  }
}
