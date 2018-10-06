package be.inniger.euler.problems11to20;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Math;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static be.inniger.euler.util.CollectionUtil.getSingleElement;
import static be.inniger.euler.util.CollectionUtil.lastIndex;
import static be.inniger.euler.util.CollectionUtil.readProblemDataAndTransform;
import static be.inniger.euler.util.Math.max;
import static java.util.stream.Collectors.toUnmodifiableList;

/*
 * Maximum path sum I
 *
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.
 * ...3...
 * ..7.4..
 * .2.4.6.
 * 8.5.9.3
 * That is, 3 + 7 + 4 + 9 = 23.
 * Find the maximum total from top to bottom of the triangle below: (see Problem18.txt)
 * NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route. However, Problem 67, is the same challenge with a triangle containing one-hundred rows; it cannot be solved by brute force, and requires a clever method! ;o)
 */
// TODO create a static inner class representing the pyramid
public class Problem18 implements Problem {

  /*
   * Start from the bottom up -- every element in the one-before-last row is still eligible (e.g. 2,4,6 in the example above).
   * The maximum path sums starting from there can be trivially calculated, take the maximum of its left- and right-children, and add them.
   * In the example above, sum 2 with max(8, 5) = 10, sum 4 with max(5, 9) = 13 and sum 6 with max(9, 3) = 15.
   * Dropping the final row this creates a new equivalent triangle as far as path sums are concerned:
   * ....3...
   * ..7..4..
   * 10.13.15
   *
   * Repeat the process until only one row remains with a single element, this is the max path sum.
   *
   * The actual (tail-recursive) algorithm below stores the new bottom column in an immutable accumulator,
   * and "drops" rows from pyramid by taking a one-row-shorted view from the also immutable pyramid.
   */
  @Override
  public long solve() {
    var pyramid = readPyramid();

    return solve(pyramid.subList(0, lastIndex(pyramid)), pyramid.get(lastIndex(pyramid)));
  }

  private long solve(List<List<Integer>> pyramid, List<Integer> acc) {
    if (pyramid.isEmpty()) {
      return getSingleElement(acc);
    }

    var lastRow = pyramid.get(lastIndex(pyramid));
    var newAcc = IntStream.iterate(0, i -> i < lastRow.size(), Math::inc)
        .mapToObj(i -> lastRow.get(i) + max(acc.get(i), acc.get(i + 1)))
        .collect(toUnmodifiableList());

    return solve(pyramid.subList(0, lastIndex(pyramid)), newAcc);
  }

  private List<List<Integer>> readPyramid() {
    var pyramid = readProblemDataAndTransform("Problem18", lines ->
        lines.map(line -> line.split(" "))
            .map(Stream::of)
            .map(numberStream -> numberStream.map(Integer::parseInt).collect(toUnmodifiableList()))
            .collect(toUnmodifiableList()));

    if (pyramid.isEmpty()) {
      throw new IllegalArgumentException("Pyramid cannot be empty!");
    }

    return pyramid;
  }
}
