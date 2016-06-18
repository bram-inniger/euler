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

package be.inniger.euler.problems01to10;

import java.util.LinkedList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem04 implements Problem {

  @NotNull
  @Override
  public String solve() {
    int largest = -1;

    for (int i = 100; i < 1000; i++) {
      for (int j = i; j < 1000; j++) {
        int prod = i * j;
        if (isPalindrome(prod) && prod > largest) {
          largest = prod;
        }
      }
    }

    return "" + largest;
  }

  /**
   * Check if a number is a palindrome.
   *
   * @param num The number to be checked
   * @return True if the number is a palindrome
   */
  private boolean isPalindrome(int num) {
    List<Integer> digits = getDigits(num);
    int size = digits.size();

    for (int i = 0; i < size / 2; i++) {
      if (digits.get(i).intValue() != digits.get(size - 1 - i).intValue()) {
        return false;
      }
    }

    return true;
  }

  /**
   * Decompose a number into a list of its digits (in the logical order).
   *
   * @param num The number to get the digits of
   * @return A list of the number's digits
   */
  private List<Integer> getDigits(int num) {
    List<Integer> digits = new LinkedList<>();

    while (num != 0) {
      digits.add(0, num%10);
      num /= 10;
    }

    return digits;
  }
}
