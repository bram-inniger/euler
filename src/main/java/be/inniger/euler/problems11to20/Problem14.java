package be.inniger.euler.problems11to20;

import be.inniger.euler.Problem;
import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

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
  private static final Function1<Long, List<Long>> COLLATZ_SEQ = Function1.of(Problem14::collatzSequence).memoized();

  private static List<Long> collatzSequence(long number) {
    if (number == 1L) return List.of(1L);

    return number % 2 == 0L ?
        COLLATZ_SEQ.apply(number / 2).prepend(number) :
        COLLATZ_SEQ.apply(number * 3 + 1).prepend(number);
  }

  @Override
  public long solve() {
    return Stream.range(1, MAX_STARTING_NUMBER)
        .map(COLLATZ_SEQ)
        .maxBy(List::length)
        .map(List::head)
        .getOrElseThrow(IllegalArgumentException::new);
  }
}
