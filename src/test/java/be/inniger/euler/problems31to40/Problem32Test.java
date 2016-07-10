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

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem32Test {

  @Test
  @Ignore // TODO write a better optimised version that takes less than 3s to solve, until then ignore this test
  public void testSolve() {
    String expected = "45228";
    String actual = new Problem32().solve();

    assertEquals(expected, actual);
  }
}