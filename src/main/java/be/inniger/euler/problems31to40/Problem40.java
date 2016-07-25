/*
 * Project Euler solution repository
 * Copyright (C) 2016 Bram Inniger
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package be.inniger.euler.problems31to40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Maths;

/**
 * Problem from Project Euler:
 * <p>
 * An irrational decimal fraction is created by concatenating the positive integers:
 * 0.12345678910_1_112131415161718192021...
 * It can be seen that the 12th digit of the fractional part is 1.
 * If d_n represents the nth digit of the fractional part, find the value of the following expression.
 * d_1 × d_10 × d_100 × d_1000 × d_10000 × d_100000 × d_1000000
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem40 implements Problem {

  // A note: the digit at index 1 is omitted, as its value is 1 anyway, not changing the product
  private static final Collection<Integer> digitIndices = Arrays.asList(10, 100, 1000, 10000, 100000, 1000000);
  private final List<Integer> groupSizes;

  public Problem40() {
    groupSizes = determineGroupSizes();
  }

  /**
   * This problem is probably just as easily solved on paper, regardless an actual solution will be discussed.
   *
   * The essential observation to make is the following:
   * First 9 1-digit numbers will occur (1 2 3 4 5 6 7 8 9)   -> the first 9*1 digits of the sequence.
   * Secondly 90 2-digit numbers appear (10 11 12 ... 99)     -> the next 90*2 digits of the sequence.
   * Thirdly 900 3-digit numbers appear (100 101 102 ... 999) -> the next 900*3 digits of the sequence.
   * ...
   *
   * We can extract a simple rule from this:
   * The d-digit numbers will "claim" [9 * 10^(d-1) * d] digits of the sequence.
   *
   * Keeping this rule in mind we can determine which number the nth digit of the sequence belongs too, and thus what value the digit is.
   */
  @NotNull
  @Override
  public String solve() {
    return "" +
        digitIndices
            .parallelStream()
            .mapToInt(this::getDigit)
            .reduce(1, Math::multiplyExact);
  }

  /**
   * As described above, there is a certain pattern in this number:
   * The first 9 digits will belong to 1 digit numbers, the next 180 to 2 digit numbers, ...
   * The formula devised above will be applied over and over again, until a value is found larger than the largest digit that will be searched (the one millionth one).
   * It is clear that there is no use calculating above, so this serves as a nice bounding value.
   *
   * @return A List containing the digit-group sizes
   */
  @SuppressWarnings("OptionalGetWithoutIsPresent") // The Collection is guaranteed to be instantiated
  private List<Integer> determineGroupSizes() {
    List<Integer> groupSizes = new ArrayList<>();

    int maxDigitIndex = digitIndices
        .stream()
        .max(Comparator.naturalOrder())
        .get();
    int totalDigitsAccounted = 0;
    int nrDigits = 0;

    while (totalDigitsAccounted < maxDigitIndex) {
      int groupSize = 9 * (int) Maths.pow(10, nrDigits-1) * nrDigits;
      groupSizes.add(groupSize);
      totalDigitsAccounted += groupSize;
      nrDigits++;
    }

    return groupSizes;
  }

  /**
   * Actually determine the digit under investigation:
   * First determine the group the number at the index belongs to.
   * When the number is found determine the actual digit inside the number the index corresponds too.
   *
   * @param i The index of the searched digit
   * @return The searched digit
   */
  private int getDigit(int i) {
    // Determine the group (group of 1 digit numbers, group of 2 digits numbers, ...)
    int group = 0;
    while (i > groupSizes.get(group)) {
      i -= groupSizes.get(group);
      group++;
    }

    // Find the number inside sequence, e.g. the 100th digit is found inside the number 55
    int num = (int) Maths.pow(10, group-1) + i / group ;

    // Find the index inside this number that is our digit.
    // E.g.  the 10th digit corresponds to the number 10, with the digit 1 that is of interest.
    int insideIndex = group - (i % group);

    return (num / (int) Maths.pow(10, insideIndex)) % 10;
  }
}
