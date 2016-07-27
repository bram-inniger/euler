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

/**
 * General class for Number-based methods.
 * So far used for triangle, pentagonal, ... number-checking.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Num {

  /**
   * Determine is a number is a triangle number.
   *
   * To verify this it suffices to solve "T_n = n * (n+1) / 2" for "n" and to verify that "n" is a positive integer number.
   *
   * T_n = n * (n+1) / 2
   * 2*T_n = n^2 + n
   * n^2 + n - 2*T_n = 0
   *
   * root 1: (-1 + sqrt(1 + 8*T_n)) / 2
   * root 2: (-1 - sqrt(1 + 8*T_n)) / 2
   *
   * As the second root is strictly negative, and we are testing for positive integers it can be discarded.
   * This leaves us with the simple test of checking whether the first root results in a positive integer or not.
   *
   * @param num The number under test
   * @return True if the number is a triangle number
   */
  public static boolean isTriangle(long num) {
    double n = (-1 + Math.sqrt(1 + 8*num)) / 2;
    return n == (long) n;
  }

  /**
   * Determine if a number is a pentagonal number.
   *
   * To verify this it suffices to solve "P_n = n * (3*n−1) / 2" for "n" and to verify that "n" is a positive integer number.
   *
   * P_n = n * (3*n−1) / 2
   * 2*P_n = 3*n^2 - n
   * 3*n^2 - n - 2*P_n = 0
   *
   * root 1: (1 + sqrt(1 + 24*P_n)) / 6
   * root 2: (1 - sqrt(1 + 24*P_n)) / 6
   *
   * As the P_n values we will test are always strictly positive the sqrt will always be greater than 1.
   * Because of this the second root will always be a strictly negative number, meaning we can safely ignore it, as it is always an invalid result.
   * This leaves us with the simple test of checking whether the first root results in a positive integer or not.
   *
   * @param num The number under test
   * @return True if the number is a pentagonal number
   */
  public static boolean isPentagonal(long num) {
    double n = (1 + Math.sqrt(1 + 24*num)) / 6;
    return n == (long) n;
  }
}
