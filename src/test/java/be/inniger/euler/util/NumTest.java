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

import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

/**
 * @author Bram Inniger
 * @version 1.0
 */
public class NumTest {

  @Test
  public void testIsTriangle() {
    String expected = "[0, 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78, 91]";
    String actual = getFilteredResult(Num::isTriangle);

    assertEquals(expected, actual);
  }

  @Test
  public void testIsPentagonal() {
    String expected = "[1, 5, 12, 22, 35, 51, 70, 92]";
    String actual = getFilteredResult(Num::isPentagonal);

    assertEquals(expected, actual);
  }

  /**
   * Shared logic to filter all numbers from 0 up to 100 (included) and return the result.
   *
   * @param predicate The predicate to use as a filter
   * @return The result of the filtering
   */
  @NotNull
  private String getFilteredResult(@NotNull IntPredicate predicate) {
    return IntStream
        .rangeClosed(0, 100)
        .filter(predicate)
        .boxed()
        .collect(Collectors.toList())
        .toString();
  }
}
