package be.inniger.euler.problems11to20;

import be.inniger.euler.Problem;

import java.math.BigInteger;

/*
 * Power digit sum
 *
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 * What is the sum of the digits of the number 2^1000?
 */
public class Problem16 implements Problem {

  private static final int EXPONENT = 1000;

  @Override
  public long solve() {
    return BigInteger.TWO.pow(EXPONENT)
        .toString()
        .chars()
        .map(Character::getNumericValue)
        .sum();
  }
}
