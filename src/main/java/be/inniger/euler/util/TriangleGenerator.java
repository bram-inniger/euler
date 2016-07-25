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

package be.inniger.euler.util;

import java.util.Iterator;

/**
 * Class that generates triangular numbers.
 * All of these adhere to "t_n = ½n(n+1)".
 * E.g.:
 * 1 -> 1           = 1
 * 2 -> 1 + 2       = 3
 * 3 -> 1 + 2 + 3   = 6
 * ...
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class TriangleGenerator implements Iterable<Long> {

  /**
   * Receive all of the generated triangle numbers in a clean way: through an iterator.
   *
   * @return Non-ending iterator, generating new triangle numbers on demand
   */
  @Override
  public Iterator<Long> iterator() {
    return new Iterator<Long>() {
      private long index = 1L;
      private long sum = 1L;

      @Override
      public boolean hasNext() {
        return true;
      }

      @Override
      public Long next() {
        long retVal = sum;
        index++;
        sum += index;

        return retVal;
      }
    };
  }

  /**
   * Method to verify if a number is indeed a triangle number or not.
   * To verify this it suffices to solve "t_n = ½n(n+1)" for "n" and verifying "n" is a positive integer number.
   *
   * t_n = ½*n*(n+1)
   * n*(n+1) - 2*t_n = 0
   * n^2 + n - 2*t_n = 0
   *
   * root 1: (-1 + sqrt(1 + 8*t_n)) / 2
   * root 2: (-1 - sqrt(1 + 8*t_n)) / 2
   *
   * As the second root is strictly negative, and we are testing for positive integers it can be discarded.
   * This leaves us with the simple test of checking whether the first root results in a positive integer or not.
   *
   * @param num The number under test
   * @return True if the number is a triangle number
   */
  public static boolean isTriangle(int num) {
    double n = (-1 + Math.sqrt(1 + 8*num)) / 2;
    return n == (int) n; // Actual Integer check
  }
}
