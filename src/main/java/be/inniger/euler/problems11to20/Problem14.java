package be.inniger.euler.problems11to20;

import be.inniger.euler.Problem;

import java.util.stream.LongStream;

import static java.util.Comparator.comparing;

/*
 * Longest Collatz sequence
 *
 * The following iterative sequence is defined for the set of positive integers:
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 * Using the rule above and starting with 13, we generate the following sequence:
 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
 * Which starting number, under one million, produces the longest chain?
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 */
public class Problem14 implements Problem {

  private static final long MAX_STARTING_NUMBER = 1_000_000L;

  @Override
  public long solve() {
    return LongStream.rangeClosed(1, MAX_STARTING_NUMBER)
        .parallel() // No memoization yet means the order of computation is completely independent and the stream may be parallel
        .mapToObj(CollatzNumber::new)
        .max(comparing(CollatzNumber::getPathLength))
        .map(CollatzNumber::getStartingNumber)
        .orElseThrow();
  }

  private static class CollatzNumber {

    private final long startingNumber;
    private final long pathLength;

    private CollatzNumber(long startingNumber) {
      this.startingNumber = startingNumber;
      this.pathLength = calculatePathLength(startingNumber);
    }

    private long getPathLength() {
      return pathLength;
    }

    private long getStartingNumber() {
      return startingNumber;
    }

    private long calculatePathLength(long number) {
      return calculatePathLengthRec(number, 0L);
    }

    private long calculatePathLengthRec(long number, long pathLengthAcc) {
      if (number == 1) {
        return pathLengthAcc;
      }

      var nextNumber = number % 2 == 0 ?
          number / 2 :
          number * 3 + 1;

      return calculatePathLengthRec(nextNumber, pathLengthAcc + 1);
    }
  }
}
