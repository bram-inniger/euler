package be.inniger.euler.problems11to20;

import be.inniger.euler.Problem;
import io.vavr.collection.List;

import java.util.stream.LongStream;

import static java.util.Comparator.comparingLong;

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
        .boxed()
        .max(comparingLong(this::calculateSequenceLength))
        .orElseThrow();
  }

  private long calculateSequenceLength(long number) {
    return calculateSequence(number).size();
  }

  private List<Long> calculateSequence(long number) {
    if (number == 1L) return List.of(1L);

    return number % 2 == 0L ?
        calculateSequence(number / 2).prepend(number) :
        calculateSequence(number * 3 + 1).prepend(number);
  }
}
