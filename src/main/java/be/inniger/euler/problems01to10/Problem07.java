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

import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Prime;

/**
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem07 implements Problem {

  private static final int PRIME_NR = 10001;

  @NotNull
  @Override
  public String solve() {
    long prime = -1L; // Not a prime, just the start value

    for (int i = 0; i < PRIME_NR; i++) {
      prime = Prime.getNext(prime);
    }

    return "" + prime;
  }
}
