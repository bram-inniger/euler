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

  private long index;
  private long sum;

  /**
   * Simple constructor supplying the starting values.
   */
  public TriangleGenerator() {
    index = 1L;
    sum = 1L;
  }

  /**
   * Receive all of the generated triangle numbers in a clean way: through an iterator.
   *
   * @return Non-ending iterator, generating new triangle numbers on demand
   */
  @Override
  public Iterator<Long> iterator() {
    return new Iterator<Long>() {

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
}
