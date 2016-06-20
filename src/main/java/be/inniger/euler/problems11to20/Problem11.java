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

import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * In the 20×20 grid below, four numbers along a diagonal line have been marked in red.
 * 08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08
 * 49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00
 * 81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65
 * 52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91
 * 22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80
 * 24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50
 * 32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70
 * 67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21
 * 24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72
 * 21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95
 * 78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92
 * 16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57
 * 86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58
 * 19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40
 * 04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66
 * 88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69
 * 04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36
 * 20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16
 * 20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54
 * 01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48
 * The product of these numbers is 26 × 63 × 78 × 14 = 1788696.
 * What is the greatest product of four adjacent numbers in the same direction (up, down, left, right, or diagonally) in the 20×20 grid?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem11 implements Problem {

  private static final String GRID_STRING =
          "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08\n" +
          "49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00\n" +
          "81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65\n" +
          "52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91\n" +
          "22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80\n" +
          "24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50\n" +
          "32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70\n" +
          "67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21\n" +
          "24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72\n" +
          "21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95\n" +
          "78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92\n" +
          "16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57\n" +
          "86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58\n" +
          "19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40\n" +
          "04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66\n" +
          "88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69\n" +
          "04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36\n" +
          "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16\n" +
          "20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54\n" +
          "01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48";

  private int greatestProd = 1;

  @NotNull
  @Override
  public String solve() {
    int[][] grid = getGrid();
    int nrRows = grid.length;
    int nrColumns = grid[0].length;

    for (int i = 0; i < nrRows; i++) {
      for (int j = 0; j < nrColumns; j++) {
        if (i <= nrRows-4) { // Search downwards
          setIfGreatest(grid[i][j] * grid[i+1][j] * grid[i+2][j] * grid[i+3][j]);
        }

        if (j <= nrColumns-4) { // Search to the right
          setIfGreatest(grid[i][j] * grid[i][j+1] * grid[i][j+2] * grid[i][j+3]);
        }

        if (i <= nrRows-4 && j <= nrColumns-4) { // Search the down-right diagonal
          setIfGreatest(grid[i][j] * grid[i+1][j+1] * grid[i+2][j+2] * grid[i+3][j+3]);
        }

        if (i <= nrRows-4 && j >= 3) { // Search the up-right diagonal
          setIfGreatest(grid[i][j] * grid[i+1][j-1] * grid[i+2][j-2] * grid[i+3][j-3]);
        }
      }
    }

    return "" + greatestProd;
  }

  /**
   * Split the constant grid above into an actually useful 2D array of ints.
   *
   * @return A 2D array representing the grid
   */
  private int[][] getGrid() {
    String[] rows = GRID_STRING.split("\n");
    int nrRows = rows.length;
    int nrColumns = rows[0].length() / 3; // For every 3 characters above there is one Integer

    int[][] grid = new int[nrRows][nrColumns];
    for (int i = 0; i < nrRows; i++) {
      String row = rows[i];

      for (int j = 0; j < nrColumns; j++) {
        grid[i][j] = Integer.parseInt(row.substring(j*3, j*3 + 2));
      }
    }

    return grid;
  }

  /**
   * Check if the current product is indeed the greatest, and mark it as such.
   *
   * @param prod The current product to investigate
   */
  private void setIfGreatest(int prod) {
    if (prod > greatestProd) {
      greatestProd = prod;
    }
  }
}
