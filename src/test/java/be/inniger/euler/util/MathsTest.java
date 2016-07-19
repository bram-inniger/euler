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

import static be.inniger.euler.util.Maths.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Bram Inniger
 * @version 1.0
 */
public class MathsTest {

  @Test
  public void testPow() {
    assertEquals(1, pow(1, 1));
    assertEquals(1, pow(1, 2));
    assertEquals(2, pow(2, 1));
    assertEquals(1024, pow(2, 10));
  }

  @Test
  public void testGetFactors() {
    assertEquals("{}", getFactors(1).toString());
    assertEquals("{2=1}", getFactors(2).toString());
    assertEquals("{2=2}", getFactors(4).toString());
    assertEquals("{2=2, 3=1, 7=1}", getFactors(84).toString());
  }

  @Test
  public void testGetDivisors() {
    assertEquals("[1]", getDivisorsOrdered(1).toString());
    assertEquals("[1, 2]", getDivisorsOrdered(2).toString());
    assertEquals("[1, 2, 3, 6, 7, 14, 21, 42]", getDivisorsOrdered(42).toString());
  }

  @Test
  public void testFac() {
    assertEquals("1", fac(0).toString());
    assertEquals("1", fac(1).toString());
    assertEquals("2", fac(2).toString());
    assertEquals("6", fac(3).toString());
    assertEquals("1405006117752879898543142606244511569936384000000000", fac(42).toString());
  }

  @Test
  public void testSmallFac() {
    assertEquals(1, smallFac(0));
    assertEquals(1, smallFac(1));
    assertEquals(2, smallFac(2));
    assertEquals(6, smallFac(3));
    assertEquals(479001600, smallFac(12));
  }

  @Test
  public void testIsAmicable() {
    assertFalse(isAmicable(1));
    assertFalse(isAmicable(2));
    assertFalse(isAmicable(42));
    assertTrue(isAmicable(220));
    assertTrue(isAmicable(284));
  }

  @Test
  public void testGetDigits() {
    assertEquals("[0]", getDigits(0L).toString());
    assertEquals("[1]", getDigits(1L).toString());
    assertEquals("[1]", getDigits(-1L).toString());
    assertEquals("[9]", getDigits(9L).toString());
    assertEquals("[9]", getDigits(-9L).toString());
    assertEquals("[1, 0]", getDigits(10L).toString());
    assertEquals("[1, 0]", getDigits(-10L).toString());
    assertEquals("[9, 9]", getDigits(99L).toString());
    assertEquals("[9, 9]", getDigits(-99L).toString());
    assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 0]", getDigits(1234567890L).toString());
  }

  @Test
  public void testGetNrDigits() {
    assertEquals(1, getNrDigits(0L));
    assertEquals(1, getNrDigits(1L));
    assertEquals(1, getNrDigits(-1L));
    assertEquals(1, getNrDigits(9L));
    assertEquals(1, getNrDigits(-9L));
    assertEquals(2, getNrDigits(10L));
    assertEquals(2, getNrDigits(-10L));
    assertEquals(2, getNrDigits(99L));
    assertEquals(2, getNrDigits(-99L));
    assertEquals(10, getNrDigits(1234567890));
  }

  @Test
  public void testIsPandigital() {
    assertTrue(isPandigital(1, 1));
    assertFalse(isPandigital(0, 1));
    assertFalse(isPandigital(1, 2));
    assertFalse(isPandigital(2, 1));
    assertFalse(isPandigital(2, 2));
    assertFalse(isPandigital(12, 1));
    assertTrue(isPandigital(12, 2));
    assertTrue(isPandigital(21, 2));
    assertFalse(isPandigital(13, 2));
    assertFalse(isPandigital(10, 2));
    assertFalse(isPandigital(11, 1));
    assertFalse(isPandigital(11, 2));

    assertTrue(isPandigital(123456789));
    assertFalse(isPandigital(123456780));
    assertFalse(isPandigital(123456799));
  }

  @Test
  public void testGetPart() {
    assertEquals(123456789, getPart(123456789, 0, 9));
    assertEquals(23456789, getPart(123456789, 1, 9));
    assertEquals(12345678, getPart(123456789, 0, 8));
    assertEquals(345, getPart(123456789, 2, 5));
  }

  @Test
  public void testGetPartWithSize() {
    assertEquals(123456789, getPart(123456789, 0, 9, 9));
    assertEquals(23456789, getPart(123456789, 1, 9, 9));
    assertEquals(12345678, getPart(123456789, 0, 8, 9));
    assertEquals(345, getPart(123456789, 2, 5, 9));
  }

  @Test
  public void testGcd() {
    assertEquals(1, gcd(1, 1));
    assertEquals(1, gcd(1, 2));
    assertEquals(1, gcd(2, 1));
    assertEquals(1, gcd(2, 3));
    assertEquals(2, gcd(4, 2));
    assertEquals(2, gcd(2, 4));
    assertEquals(2, gcd(2, 2));
    assertEquals(49, gcd(49, 98)); // From Problem33
  }

  @Test
  public void testConcatenate() {
    assertEquals(1, concatenate(0L, 1L));
    assertEquals(10, concatenate(1L, 0L));
    assertEquals(1234, concatenate(1L, 234L));
    assertEquals(1234, concatenate(12L, 34L));
    assertEquals(1234, concatenate(123L, 4L));
  }
}
