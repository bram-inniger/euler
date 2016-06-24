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

package be.inniger.euler.problems21to30;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.
 * For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.
 * What is the total of all the name scores in the file?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem22 implements Problem {

  @NotNull
  @Override
  public String solve() {
    List<String> names;
    try {
      names = readNames();
    }
    catch (Exception ignore) {
      throw new RuntimeException("Could not read the names file, halting the program... ");
    }

    names.sort(Comparator.naturalOrder());
    long sum = 0L;

    for (int i = 0; i < names.size(); i++) {
      sum += getNameValue(names.get(i)) * (i+1); // Element with index 0 is the first element -> i+1
    }

    return "" + sum;
  }

  /**
   * Read the names file into a List
   *
   * @return All of the names in the file in List form
   * @throws FileNotFoundException Should the file be missing
   */
  @NotNull
  private List<String> readNames() throws FileNotFoundException {
    List<String> names = new ArrayList<>();
    Scanner scanner = new Scanner(new File("src/main/resources/problems/Problem22.txt"));
    scanner.useDelimiter(",");

    while (scanner.hasNext()) {
      String quotedName = scanner.next(); // E.g. "MARI"
      String name = quotedName.substring(1, quotedName.length()-1); // E.g. MARI (without the quotes)
      names.add(name);
    }

    return names;
  }

  /**
   * Get the value of a name as the sum of its letters.
   * To get the letter values
   *    Say 'C' has a numeric value of x (the value is unimportant).
   *    We want to somehow map 'C' to 3.
   *    But if 'C' has a value x, then 'A' has a value x-2.
   *    Thus if we take the actual value of 'C', subtract 'A' and add 1 we get:
   *       x - (x-2) + 1 = 3
   *
   * @param name The name to get the value of
   * @return The value of the name
   */
  private int getNameValue(String name) {
    return name.chars()
        .map(c -> c - 'A' + 1)
        .sum();
  }
}
