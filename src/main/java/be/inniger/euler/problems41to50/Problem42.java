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

package be.inniger.euler.problems41to50;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Misc;
import be.inniger.euler.util.TriangleGenerator;

/**
 * Problem from Project Euler:
 * <p>
 * The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t_10. If the word value is a triangle number then we shall call the word a triangle word.
 * Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, how many are triangle words?
 * <p>
 * N.B. To keep in line with the rest of this repository the file was renamed from "words.txt" to "Problem42.txt".
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem42 implements Problem {

  private static final String PATH = "src/main/resources/problems/Problem42.txt";

  /**
   * Straightforward solution of simply reading the words, converting them to the values, and filtering the triangle ones.
   */
  @SuppressWarnings("OptionalGetWithoutIsPresent")
  @NotNull
  @Override
  public String solve() {
    return "" +
        getWordValues()
            .parallelStream()
            .filter(TriangleGenerator::isTriangle)
            .count();
  }

  /**
   * Reads the file in PATH into a List, then maps all of these onto a list of corresponding word-values.
   *
   * @return A list containing the word values of the words in PATH
   */
  private List<Integer> getWordValues() {
    // Read the file
    List<String> words;
    try {
      words = Misc.readWords(new File(PATH), ",");
    }
    catch (Exception ignore) {
      throw new RuntimeException("Could not read the words file, halting the program... ");
    }

    // Convert the words into their values
    return words
        .parallelStream()
        .mapToInt(this::getWordValue)
        .boxed()
        .collect(Collectors.toList());
  }

  /**
   * Get the value of a word, by converting the characters into numbers and summing these (A=1, B=2, ...)
   *
   * @param word The word to get the value of
   * @return The value of the word according to the rule described
   */
  private int getWordValue(String word) {
    return word
        .chars()
        .map(c -> c - 'A' + 1)  // "A" becomes 1, "B" becomes 2, ...
        .sum();
  }
}
