package be.inniger.euler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public final class StreamUtil {

  private StreamUtil() {
    throw new IllegalStateException("Utility class constructor should never be called!");
  }

  public static <T> Stream<T> reverseStream(List<T> list) {
    return IntStream.iterate(list.size() - 1, Math::isStrictlyPositive, Math::dec)
        .mapToObj(list::get);
  }

  public static <R> R readProblemDataAndTransform(String problem, ProblemTransformer<R> transformer) {
    try (var is = Thread.currentThread().getContextClassLoader().getResourceAsStream("problems/" + problem + ".txt");
         var isr = new InputStreamReader(is);
         var br = new BufferedReader(isr)) {
      var lines = br.lines() // Results in Stream<String>, 1 String per line in the file
          .filter(not(String::isEmpty)); // Remove empty lines
      return transformer.transform(lines);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @FunctionalInterface
  public interface ProblemTransformer<R> {
    R transform(Stream<String> lines);
  }
}
