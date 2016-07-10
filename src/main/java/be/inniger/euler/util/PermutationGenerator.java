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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Generates permutations of type R over n elements of type T
 * <p>
 * The general idea is as follows:
 * 1. Take out every element separately
 * 2. Check all of the permutations that start with this element, followed by all remaining n-1 elements
 * 3. To keep lexicographical ordering the "higher" elements should be handled last
 * <p>
 * A problem like this simply screams recursion.
 * Unfortunately it is a bit more neat if these values can be iterated over, one by one, and only calculated when needed.
 * For this a Stack data-structure is used, allowing to easily keep track of work left to be done.
 * <p>
 * Check the subclass DigitPermutationGenerator for a full explanation on the layout of the Stack, and how it interacts with the algorithm.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public abstract class PermutationGenerator<T, R> implements Iterator<R> {

  private static final Logger log = LogManager.getLogger(DigitPermutationGenerator.class);
  private final Stack<Permutation> stack;

  /**
   * Let the subclass initiate the Stack with a proper starting value.
   *
   * @param nrElements The nr of elements to make permutations of
   */
  protected PermutationGenerator(int nrElements) {
    this.stack = initStack(nrElements);
  }

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
   * Return the next permutation.
   * The general idea is to see if the top of the Stack contains a concrete permutation (all elements fixed).
   * If not simply pop the top, and decompose it into different permutations with more fixed elements.
   *
   * @return The next permutation
   */
  @NotNull
  @Override
  public R next() {
    while (!stack.peek().isConcrete()) {        // Step 1, check if the current top of the Stack is already a concrete permutation
      log.trace("Current Stack: {}", stack);
      decomposeTopPermutation();                // Step 2, decompose the top element into smaller elements to continue work on
    }

    return stack.pop().permutation;
  }

  /**
   * Initiate the Stack via the implementing class.
   *
   * @return A now properly initialised Stack
   */
  @NotNull
  protected abstract Stack<Permutation> initStack(int nrElements);

  /**
   * Pop the top of the Stack, and decompose the elements to be handled into new Stack elements.
   */
  private void decomposeTopPermutation() {
    Permutation top = stack.pop();

    for (int i = top.elements.size() - 1; i >= 0; i--) {
      List<T> newElements = new ArrayList<>(top.elements);
      T element = newElements.remove(i);                        // Pick one element from the to-be-processed list, and remove it
      R newPermutation = combine(top.permutation, element);     // Add the new element to the permutation found so far

      stack.push(new Permutation(newPermutation, newElements)); // Push this new simpler Permutation (less "free" elements to permute over) on the Stack
    }
  }

  /**
   * Combine the part of the permutation found so far, with the new element takes from the available elements.
   *
   * @param permutation The permutation so far
   * @param element     The new element to add to the fixed part of the permutation
   * @return The new fixed part of the permutation (the combination of the 2 arguments above)
   */
  @NotNull
  protected abstract R combine(@NotNull R permutation, @NotNull T element);

  /**
   * Inner class representing a Permutation in progress.
   * It only contains the actual concrete part of the permutation found so far and the digits that still need to be permuted over.
   */
  protected class Permutation {

    private final R permutation;
    private final List<T> elements;

    /**
     * Allows to create a new Permutation object in a specific state.
     *
     * @param permutation Part of the permutation already discovered
     * @param elements    More elements still to iterate over
     */
    protected Permutation(@NotNull R permutation, @NotNull List<T> elements) {
      this.permutation = permutation;
      this.elements = elements;
    }

    /**
     * If no elements are left to permute over we know the current value of "permutation" is the final one.
     *
     * @return True if the Permutation object holds a concrete permutation
     */
    private boolean isConcrete() {
      return elements.size() == 0;
    }

    /**
     * Simple method useful during debugging.
     *
     * @return A String representing the current permutation (elements already fixed, and elements still to go)
     */
    @NotNull
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder()
          .append(permutation)
          .append(", [");              // Open up the brackets for the remaining elements part

      elements.forEach(el -> sb.append(el).append(" "));

      return sb
          .deleteCharAt(sb.length() - 1) // Delete last space added by the forEach call
          .append(']')                   // Close up the brackets for the remaining elements part
          .toString();
    }
  }
}
