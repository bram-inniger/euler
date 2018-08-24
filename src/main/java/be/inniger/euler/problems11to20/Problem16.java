package be.inniger.euler.problems11to20;

import be.inniger.euler.util.Math;

import java.math.BigInteger;

/**
 * Power digit sum
 * <p>
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 * What is the sum of the digits of the number 2^1000?
 */
public class Problem16 {

  private static final int EXPONENT = 1000;

  public int solve() {
    return BigInteger.TWO.pow(EXPONENT)
        .toString()
        .chars()
        .mapToObj(Character::getNumericValue)
        .reduce(Math::sum)
        .orElseThrow();
  }
}
