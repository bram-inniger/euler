package be.inniger.euler.problems01to10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toUnmodifiableList;

public class Problem08 {

  private static final int NR_ADJACENT_DIGITS = 13;
  private final List<Integer> number = readNumber();

  public long solve() {
    return IntStream.rangeClosed(0, number.size() - NR_ADJACENT_DIGITS)
        .boxed()
        .map(beginIndex -> number.subList(beginIndex, beginIndex + NR_ADJACENT_DIGITS))
        .mapToLong(digits -> digits.stream()
            .map(Integer::longValue)
            .reduce(1L, java.lang.Math::multiplyExact))
        .max()
        .orElseThrow();
  }

  private List<Integer> readNumber() {
    try (var is = Thread.currentThread().getContextClassLoader().getResourceAsStream("problems/Problem08.txt");
         var isr = new InputStreamReader(is);
         var br = new BufferedReader(isr)) {
      return br.lines()
          .filter(line -> !line.isEmpty()) // Ignore empty lines
          .flatMap(line -> line.chars()
              .boxed()
              .map(Character::getNumericValue))
          .collect(toUnmodifiableList());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
