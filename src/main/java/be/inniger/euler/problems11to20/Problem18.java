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

package be.inniger.euler.problems11to20;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.CataclysmicException;

/**
 * Problem from Project Euler:
 * <p>
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.
 * ...3
 * ..7 4
 * .2 4 6
 * 8 5 9 3
 * That is, 3 + 7 + 4 + 9 = 23.
 * Find the maximum total from top to bottom of the triangle below:
 * <p>
 * Check Problem18.txt
 * <p>
 * NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route. However, Problem 67, is the same challenge with a triangle containing one-hundred rows; it cannot be solved by brute force, and requires a clever method! ;o)
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem18 implements Problem {

  /**
   * The simple yet elegant way of solving this is working from the bottom up, targeting the one-but-last row.
   * In the small example above, take the row "2 4 6"
   * We cannot yet tell whether the 2, 4 or 6 will be in the final path.
   * However: we can calculate all following paths.
   * <p>
   * If 2 is in the eventual path, then the path will either continue to 8 or 5 below it.
   * It is trivially to see the largest path is through 8.
   * So starting from 2 (included) the path that follows will be 10 long.
   * <p>
   * Likewise we can say that, starting from 4 (included) the path will be 13 long.
   * Finally, starting from 6 (included) the path will be 15 long.
   * <p>
   * Replace now the numbers "2 4 6" with "10 13 15" and remove / ignore the very last line of the pyramid.
   * The resulting pyramid's solution will be exactly the same as the original pyramid's solution.
   * <p>
   * Repeat this process by removing the last line until only the top line / element remains, this will automatically be the answer.
   *
   * @return The sum of the largest path
   */
  @NotNull
  @Override
  public String solve() {
    List<List<Integer>> pyramid = readPyramid();

    for (int i = pyramid.size()-2; i >= 0; i--) { // i starts at length - 2 -> the one-but-last row
      List<Integer> current = pyramid.get(i);
      List<Integer> remove = pyramid.get(i+1);

      for (int j = 0; j < current.size(); j++) {
        int maxNext = Math.max(remove.get(j), remove.get(j+1));
        current.set(j, current.get(j) + maxNext); // Replace every element by itself + the largest path that follows
      }

      pyramid.remove(i+1); // Not needed, but shows the actual intent of the algorithm
    }

    return "" + pyramid.get(0).get(0); // The top
  }

  /**
   * Read the pyramid text file, and parse all data as Integers.
   * Every 3 characters represent 1 number, except for the last number (no trailing space), so they will be parsed as such.
   *
   * @return A List of Lists, representing the pyramid in number form
   */
  private List<List<Integer>> readPyramid() {
    List<List<Integer>> pyramid = new ArrayList<>();
    File numbersFile = new File("src/main/resources/problems/Problem18.txt");

    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(numbersFile)));
      String line;

      while ((line = br.readLine()) != null) {
        List<Integer> row = new ArrayList<>();

        for (int i = 0; i < (line.length() + 1) / 3; i++) { // +1 to length to counter the lack of a trailing space for the last number
          row.add(Integer.parseInt(line.substring(3 * i, 3 * i + 2)));
        }

        pyramid.add(row);
      }
    }
    catch (IOException e) {
      throw new CataclysmicException(e);
    }

    return pyramid;
  }
}
