package be.inniger.euler.util;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamUtil {

  private StreamUtil() {
    throw new IllegalStateException("Utility class constructor should never be called!");
  }

  public static <T> Stream<T> reverseStream(List<T> list) {
    return IntStream.iterate(list.size() - 1, i -> i >= 0, i -> i - 1)
        .mapToObj(list::get);
  }
}
