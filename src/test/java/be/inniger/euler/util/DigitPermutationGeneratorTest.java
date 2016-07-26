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
public class DigitPermutationGeneratorTest {

  /**
   * Straightforward test: generate all the iterations of the number 1, 2 and 3, then verify all of them passed by.
   * This checks both the Iterator#next() and Iterator#hasNext() methods, via the default Iterator#forEachRemaining.
   */
  @Test
  public void testGeneratorFromOne() {
    String expected = "[123, 132, 213, 231, 312, 321]";
    String actual = testGenerator(new DigitPermutationGenerator(1, 3));
    assertEquals(expected, actual);
  }

  /**
   * Straightforward test: generate all the iterations of the number 0, 1 and 2, then verify all of them passed by.
   * This checks both the Iterator#next() and Iterator#hasNext() methods, via the default Iterator#forEachRemaining.
   */
  @Test
  public void testGeneratorFromZero() {
    String expected = "[12, 21, 102, 120, 201, 210]";
    String actual = testGenerator(new DigitPermutationGenerator(0, 2));
    assertEquals(expected, actual);
  }

  /**
   * Run the actual to be tested logic for any DigitPermutationGenerator, and return a List of generated permutations.
   *
   * @param gen The PermutationGenerator that is used
   * @return A String representing the generated permutations
   */
  private String testGenerator(DigitPermutationGenerator gen) {
    List<Long> permutations = new ArrayList<>();
    gen.forEachRemaining(permutations::add);

    return permutations.toString();
  }
}
