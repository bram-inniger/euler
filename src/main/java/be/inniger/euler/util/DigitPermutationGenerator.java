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

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

/**
 * Generates permutations over all digits in the 1 to n range (inclusive).
 * Check the parent class for the general idea on how to generate permutations.
 * <p>
 * <p>
 * A full example to demonstrate how in this case the algorithm will play out:
 * Say n is equal to 3, then the starting state of the Stack would be:
 * 0, [1 2 3]
 * This means no digits yet have been "fixed" and all digits still need to be iterated over.
 * <p>
 * <p>
 * The algorithm then goes as follows:
 * 1. Check if the top of the Stack is a concrete permutation, if so pop it from the Stack and return it, done!
 * <p>
 * 2. If not then pop the top of the Stack and decompose it into all of the possible digits.
 * Prev state of the Stack:
 *    0, [1 2 3]
 * <p>
 * Current state:
 *    1, [2 3]
 *    2, [1 3]
 *    3, [1 2]
 * Read the first line as "every number starting with 1, followed by any permutation of the digits 2 and 3".
 * <p>
 * 3. Still no concrete permutation is on the Stack, so the same operation gets performed, pop the top (1, [2 3]) and decompose
 * New state of the Stack:
 *    12, [3]
 *    13, [2]
 *    2, [1 3]
 *    3, [1 2]
 * <p>
 * 4. Now we are very close to an actual number, again pop the top and decompose, resulting in the following Stack:
 *    123, []
 *    13, [2]
 *    2, [1 3]
 *    3, [1 2]
 * This operation will now return the number "123" and will be ready .
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class DigitPermutationGenerator extends PermutationGenerator<Integer, Long> {

  /**
   * Constructor calling the parent constructor.
   *
   * @param n Number of digits to iterate over (1 -> n, inclusive)
   */
  public DigitPermutationGenerator(int n) {
    super(n);
  }

  /**
   * Initiate the Stack for the specific purpose of digit-permutations.
   *
   * @return A now properly initialised Stack
   */
  @NotNull
  @Override
  protected Stack<Permutation> initStack(int nrElements) {
    List<Integer> digits = IntStream
        .rangeClosed(1, nrElements)
        .boxed()
        .collect(Collectors.toList());         // Generate List containing all digits of the permutations

    Stack<Permutation> stack = new Stack<>();  // Create a new Stack
    stack.push(new Permutation(0L, digits));   // Push the starting value on the Stack: "no starting val + all digits left to permute over

    return stack;
  }

  /**
   * Combine the next digit together with all of the digits already fixed of the permutation
   *
   * @param permutation The permutation discovered so far
   * @param element The next element (in this case digit) to add
   * @return The combined value (the new permutation so far)
   */
  @NotNull
  @Override
  protected Long combine(@NotNull Long permutation, @NotNull Integer element) {
    return 10 * permutation + element;
  }
}
