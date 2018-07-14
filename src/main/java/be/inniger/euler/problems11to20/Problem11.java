package be.inniger.euler.problems11to20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.toList;

/**
 * Largest product in a grid
 * <p>
 * In the 20×20 grid below, four numbers along a diagonal line have been marked in red.
 * The product of these numbers is 26 × 63 × 78 × 14 = 1788696.
 * What is the greatest product of four adjacent numbers in the same direction (up, down, left, right, or diagonally) in the 20×20 grid?
 */
public class Problem11 {

  private static final int NR_ADJACENT = 4;

  private final List<List<Integer>> grid = readGrid();

  public int solve() {
    if (doesGridFailValidation()) {
      throw new IllegalArgumentException("Grid needs to be rectangular!"); // Verify if the grid is indeed a grid
    }

    return IntStream.range(0, grid.size()) // 0 until 20, y-coordinate
        .boxed()
        .flatMap(y ->
            IntStream.range(0, grid.get(0).size()) // 0 until 20, x-coordinate
                .mapToObj(x -> new Coordinate(x, y))) // Put x and y together in a coordinate
        // This is now a List<Coordinate> containing all possible grid positions
        .flatMap(coordinate ->
            Stream.of(Direction.values()) // Per coordinate, look in all (de-duplicated) directions (e.g. if you look down you don't need to look up)
                .map(direction ->
                    new Line(coordinate, direction))) // Draw a line from a coordinate in a directions (line = 4 adjacent numbers)
        // This is now a List<Line> containing all possible lines that can be drawn from all possible grid positions
        .filter(Line::isValid) // Only keep lines that fit inside the grid (e.g. if the origin is on the final row it does not make sense to have a line downwards)
        .map(Line::product) // Of all valid lines calculate the product of the adjacent numbers
        .max(naturalOrder()) // Get the greatest product
        .orElseThrow(IllegalArgumentException::new);
  }

  private List<List<Integer>> readGrid() {
    try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("problems/Problem08.txt");
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(isr)) {
      return br.lines() // Results in Stream<String>, 1 String per line in the file
          .filter(line -> !line.isEmpty()) // Ignore empty lines
          .map(line -> line.split(" "))
          .map(Stream::of) // Results in Stream<Stream<String>>, split every line in its separate String values
          .map(numberStream -> numberStream.map(Integer::parseInt).collect(toList()))
          .collect(toList()); // Results in List<List<Integer>>, the grid, by converting every single String into an Integer
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private boolean doesGridFailValidation() {
    return grid.stream()
        .map(List::size) // For the grid to be a rectangle, every row needs to have the same length
        .distinct() // Get all the different row lengths in the grid
        .count() != 1; // If there is more than 1 different length, the grid is not rectangular, and thus not a valid grid
  }

  private enum Direction {
    DOWNWARD(0, 1),
    RIGHTWARD(1, 0),
    UP_RIGHT(1, -1),
    DOWN_RIGHT(1, 1);

    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {
      this.deltaX = deltaX;
      this.deltaY = deltaY;
    }
  }

  private class Coordinate {
    private final int x;
    private final int y;

    private Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private class Line {

    private final List<Coordinate> line;

    private Line(Coordinate origin, Direction direction) {
      // Start from the coordinate, and walk in a Direction, to get 4 adjacent coordinates
      this.line = IntStream.range(0, NR_ADJACENT)
          .mapToObj(i -> new Coordinate(
              origin.x + i * direction.deltaX,
              origin.y + i * direction.deltaY))
          .collect(toList());
    }

    // A line will be inside the rectangle if both ends of the line are inside
    private boolean isValid() {
      return line.get(0).x >= 0 && line.get(0).x < grid.get(0).size() &&
          line.get(0).y >= 0 && line.get(0).y < grid.size() &&
          line.get(line.size() - 1).x >= 0 && line.get(line.size() - 1).x < grid.get(0).size() &&
          line.get(line.size() - 1).y >= 0 && line.get(line.size() - 1).y < grid.size();
    }

    // Calculate the product of the 4 adjacent numbers on the line
    private int product() {
      return line.stream()
          .map(coordinate -> grid.get(coordinate.y).get(coordinate.x))
          .reduce((acc, factor) -> acc * factor)
          .orElseThrow(IllegalArgumentException::new);
    }
  }
}
