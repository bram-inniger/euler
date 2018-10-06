package be.inniger.euler.util;

import be.inniger.euler.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public final class CollectionUtil {

  private CollectionUtil() {
    throw new IllegalStateException("Utility class constructor should never be called!");
  }

  public static <T> Stream<T> reverseStream(List<T> list) {
    return IntStream.iterate(lastIndex(list), Math::isStrictlyPositive, Math::dec)
        .mapToObj(list::get);
  }

  public static <R> R readProblemDataAndTransform(Problem problem, ProblemTransformer<R> transformer) {
    try (var is = Thread.currentThread().getContextClassLoader().getResourceAsStream("problems/" + problem.getClass().getSimpleName() + ".txt");
         var isr = new InputStreamReader(is);
         var br = new BufferedReader(isr)) {
      var lines = br.lines() // Results in Stream<String>, 1 String per line in the file
          .filter(not(String::isEmpty)); // Remove empty lines
      return transformer.transform(lines);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T getSingleElement(Collection<T> collection) {
    if (collection == null || collection.size() != 1) {
      throw new IllegalArgumentException("Collection did not have size 1!");
    }

    return collection.stream()
        .findAny()
        .orElseThrow();
  }

  public static int lastIndex(List<?> list) {
    if (list == null) {
      throw new IllegalArgumentException("List cannot be null!");
    }

    return list.size() - 1;
  }

  @FunctionalInterface
  public interface ProblemTransformer<R> {
    R transform(Stream<String> lines);
  }
}
