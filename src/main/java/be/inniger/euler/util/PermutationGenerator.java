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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Generates permutations over all digits in the 1 to n range (inclusive).
 * The basic idea is to understand there will be a bunch of numbers starting with 1, then a bunch with 2, then ..., finally n.
 * Let P generate all permutations, then we can say:
 * P (1 -> n) =
 *               (1, P(2 -> n)) + (2, P(1 -> n, exclude 2)) + ... + (n, P(1 -> n-1))
 *
 * Naturally if one wanted to generate all of them at the same time this would call for a very simple recursive function, and that would be the end of it.
 * Unfortunately this class is written to be used as an Iterator, meaning 1 element at a time.
 * For that reason a similar strategy as recursion is chosen by explicitly keeping track of the work left to do via a Stack.
 *
 *
 * A full example:
 * Say n is equal to 3, then the starting state of the Stack would be:
 * 0, [1 2 3]
 * This means no digits yet have been "fixed" and all digits still need to be iterated over.
 *
 * The algorithm then goes as follows:
 * 1. Check if the top of the Stack is a concrete permutation, if so pop it from the Stack and return it, done!
 * 2. If not then pop the top of the Stack and decompose it into all of the possible digits.
 *    Prev state of the Stack:
 *      0, [1 2 3]
 *
 *    Current state:
 *      1, [2 3]
 *      2, [1 3]
 *      3, [1 2]
 *
 *      Read this as "every number starting with 1, followed by any permutation of the digits 2 and 3" and likewise for "2" and "3"
 * 3. Go back to step 1 and repeat.
 *
 *
 * Still no concrete permutation is on the Stack, so the same operation gets performed, pop the top (1, [2 3]) and decompose
 * New state of the Stack:
 *   12, [3]
 *   13, [2]
 *   2, [1 3]
 *   3, [1 2]
 * No we are very close to an actual number, again pop the top and decompose, resulting in the following Stack:
 *   123, []
 *   13, [2]
 *   2, [1 3]
 *   3, [1 2]
 * This operation will now return the number "123" and will be ready .
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class PermutationGenerator implements Iterable<Long> {

  private static final Logger log = LogManager.getLogger(PermutationGenerator.class);
  private final List<Integer> digits;

  /**
   * Constructor generating the starting List of all digits to iterate over.
   *
   * @param n Number of digits to iterate over (1 -> n, inclusive)
   */
  public PermutationGenerator(int n) {
    digits = IntStream
        .rangeClosed(1, n)
        .boxed()
        .collect(Collectors.toList());
  }

  /**
   * Create a new Iterator, useful in enhanced for-loops to easily generate permutations.
   *
   * @return An Iterator returning all permutations in lexicographical order
   */
  @Override
  public Iterator<Long> iterator() {
    return new Iterator<Long>() {

      private final Stack<Permutation> stack = initStack();

      /**
       * Once the Stack is empty we know all permutations have been found.
       *
       * @return True if there are still permutations left to be discovered.
       */
      @Override
      public boolean hasNext() {
        return !stack.empty();
      }

      /**
       * Return the next permutation, follow the algorithm describe above.
       *
       * @return The next permutation
       */
      @Override
      public Long next() {
        while (!stack.peek().isConcrete()) {        // Step 1, check if the current top of the Stack is already a concrete permutation
          log.trace("Current Stack: {}", stack);
          decomposeTopPermutation();                // Step 2, decompose the top element into smaller elements to continue work on
        }

        return stack.pop().permutation;
      }

      /**
       * Initiate the Stack on object creation (similar to a constructor, but for an anonymous inner class).
       *
       * @return A now properly initialised Stack
       */
      private Stack<Permutation> initStack() {
        Stack<Permutation> stack = new Stack<>();
        stack.push(new Permutation());              // Add the default starting value (no-args constructor)
        return stack;
      }

      /**
       * Pop the top of the Stack, and decompose it using the digits still to be iterated over, forming new Permutation objects with each.
       * Check the explanation on top see how / why this works in more detail.
       */
      private void decomposeTopPermutation() {
        Permutation top = stack.pop();

        for (int i = top.digits.size() - 1; i >= 0; i--) {
          List<Integer> newDigits = new ArrayList<>(top.digits);
          int digit = newDigits.remove(i);                       // Pick one digit from the to-be-processed list, and remove it.
          long newPermutation = 10*top.permutation + digit;      // Add the removed digit to the permutation calculated so far

          stack.push(new Permutation(newPermutation, newDigits));
        }
      }
    };
  }

  /**
   * Inner class representing a Permutation in progress.
   * It only contains the actual concrete part of the permutation found so far and the digits that still need to be permuted over.
   */
  private class Permutation {

    private final long permutation;
    private final List<Integer> digits;

    /**
     * Default constructor, usable for starting a new Stack as it is the expected first value.
     */
    private Permutation() {
      permutation = 0L;
      digits = PermutationGenerator.this.digits;
    }

    /**
     * Actual constructor, allowing to create a new Permutation object in a specific state.
     *
     * @param permutation Amount of digits of the permutation already discovered
     * @param digits More digits still to do
     */
    private Permutation(long permutation, @NotNull List<Integer> digits) {
      this.permutation = permutation;
      this.digits = digits;
    }

    /**
     * If no digits are left to permute over we know the current value of "permutation" is the final one.
     *
     * @return True if the Permutation object holds a concrete permutation
     */
    private boolean isConcrete() {
      return digits.size() == 0;
    }

    /**
     * Simple method useful during debugging.
     *
     * @return A String representing the current permutation (digits already fixed, and digits still to go)
     */
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder()
          .append(permutation)
          .append(", [");              // Open up the brackets for the remaining digits

      digits.forEach(i -> sb.append(i).append(" "));

      return sb
          .deleteCharAt(sb.length()-1) // Delete last space added by the forEach call
          .append(']')                 // Close up the brackets for the remaining digits
          .toString();
    }
  }
}
