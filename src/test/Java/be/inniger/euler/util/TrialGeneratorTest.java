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

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * @author Bram Inniger
 * @version 1.0
 */
public class TrialGeneratorTest {

  /**
   * Test the Iterator functionality by iteratively asking the first 10 triangle numbers.
   */
  @Test
  public void testGenerator() {
    TriangleGenerator gen = new TriangleGenerator();
    List<Long> triangles = new ArrayList<>();

    int nrSeen = 0;
    for (long triangle : gen) {
      triangles.add(triangle);
      nrSeen++;

      if (nrSeen >= 10) {
        break;
      }
    }

    String expected = "[1, 3, 6, 10, 15, 21, 28, 36, 45, 55]";
    String actual = triangles.toString();
    assertEquals(expected, actual);
  }
}
