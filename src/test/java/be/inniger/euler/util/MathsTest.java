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
    assertEquals(1, Maths.pow(1, 1));
    assertEquals(1, Maths.pow(1, 2));
    assertEquals(2, Maths.pow(2, 1));
    assertEquals(1024, Maths.pow(2, 10));
  }

  @Test
  public void testGetFactors() {
    assertEquals("{}", Maths.getFactors(1).toString());
    assertEquals("{2=1}", Maths.getFactors(2).toString());
    assertEquals("{2=2}", Maths.getFactors(4).toString());
    assertEquals("{2=2, 3=1, 7=1}", Maths.getFactors(84).toString());
  }

  @Test
  public void testGetDivisors() {
    assertEquals("[1]", Maths.getDivisorsOrdered(1).toString());
    assertEquals("[1, 2]", Maths.getDivisorsOrdered(2).toString());
    assertEquals("[1, 2, 3, 6, 7, 14, 21, 42]", Maths.getDivisorsOrdered(42).toString());
  }

  @Test
  public void testCalculateFactorial() {
    assertEquals("1", Maths.calculateFactorial(1).toString());
    assertEquals("2", Maths.calculateFactorial(2).toString());
    assertEquals("6", Maths.calculateFactorial(3).toString());
    assertEquals("1405006117752879898543142606244511569936384000000000", Maths.calculateFactorial(42).toString());
  }

  @Test
  public void testCalculateSmallFactorial() {
    assertEquals(1, Maths.calculateSmallFactorial(1));
    assertEquals(2, Maths.calculateSmallFactorial(2));
    assertEquals(6, Maths.calculateSmallFactorial(3));
    assertEquals(479001600, Maths.calculateSmallFactorial(12));
  }

  @Test
  public void testIsAmicable() {
    assertFalse(Maths.isAmicable(1));
    assertFalse(Maths.isAmicable(2));
    assertFalse(Maths.isAmicable(42));
    assertTrue(Maths.isAmicable(220));
    assertTrue(Maths.isAmicable(284));
  }

  @Test
  public void testGetDigits() {
    assertEquals("[0]", Maths.getDigits(0L).toString());
    assertEquals("[1]", Maths.getDigits(1L).toString());
    assertEquals("[1]", Maths.getDigits(-1L).toString());
    assertEquals("[9]", Maths.getDigits(9L).toString());
    assertEquals("[9]", Maths.getDigits(-9L).toString());
    assertEquals("[1, 0]", Maths.getDigits(10L).toString());
    assertEquals("[1, 0]", Maths.getDigits(-10L).toString());
    assertEquals("[9, 9]", Maths.getDigits(99L).toString());
    assertEquals("[9, 9]", Maths.getDigits(-99L).toString());
    assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 0]", Maths.getDigits(1234567890L).toString());
  }

  @Test
  public void testGetNrDigits() {
    assertEquals(1, Maths.getNrDigits(0L));
    assertEquals(1, Maths.getNrDigits(1L));
    assertEquals(1, Maths.getNrDigits(-1L));
    assertEquals(1, Maths.getNrDigits(9L));
    assertEquals(1, Maths.getNrDigits(-9L));
    assertEquals(2, Maths.getNrDigits(10L));
    assertEquals(2, Maths.getNrDigits(-10L));
    assertEquals(2, Maths.getNrDigits(99L));
    assertEquals(2, Maths.getNrDigits(-99L));
    assertEquals(10, Maths.getNrDigits(1234567890));
  }

  @Test
  public void testIsPandigital() {
    assertTrue(Maths.isPandigital(1, 1));
    assertFalse(Maths.isPandigital(0, 1));
    assertFalse(Maths.isPandigital(1, 2));
    assertFalse(Maths.isPandigital(2, 1));
    assertFalse(Maths.isPandigital(2, 2));
    assertFalse(Maths.isPandigital(12, 1));
    assertTrue(Maths.isPandigital(12, 2));
    assertTrue(Maths.isPandigital(21, 2));
    assertFalse(Maths.isPandigital(13, 2));
    assertFalse(Maths.isPandigital(10, 2));
    assertFalse(Maths.isPandigital(11, 1));
    assertFalse(Maths.isPandigital(11, 2));
  }

  @Test
  public void testGetPart() {
    assertEquals(123456789, Maths.getPart(123456789, 0, 9));
    assertEquals(23456789, Maths.getPart(123456789, 1, 9));
    assertEquals(12345678, Maths.getPart(123456789, 0, 8));
    assertEquals(345, Maths.getPart(123456789, 2, 5));
  }

  @Test
  public void testGetPartWithSize() {
    assertEquals(123456789, Maths.getPart(123456789, 0, 9, 9));
    assertEquals(23456789, Maths.getPart(123456789, 1, 9, 9));
    assertEquals(12345678, Maths.getPart(123456789, 0, 8, 9));
    assertEquals(345, Maths.getPart(123456789, 2, 5, 9));
  }
}
