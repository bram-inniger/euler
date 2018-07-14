package be.inniger.euler.problems01to10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem08 {

  private static final int NR_ADJACENT_DIGITS = 13;
  private final List<Integer> number = readNumber();

  public long solve() {
    return IntStream.rangeClosed(0, number.size() - NR_ADJACENT_DIGITS)
        .boxed()
        .map(beginIndex -> number.subList(beginIndex, beginIndex + NR_ADJACENT_DIGITS))
        .map(digits -> digits.stream()
            .map(Integer::longValue)
            .reduce(1L,  (left, right) -> left * right))
        .max(Comparator.naturalOrder())
        .orElseThrow(IllegalArgumentException::new);
  }

  private List<Integer> readNumber() {
    try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("problems/Problem08.txt");
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(isr)) {
      return br.lines()
          .filter(line -> !line.isEmpty()) // Ignore empty lines
          .flatMap(line -> line.chars()
              .boxed()
              .map(Character::getNumericValue))
          .collect(Collectors.toList());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
