package be.inniger.euler;

import be.inniger.euler.problems01to10.*;
import be.inniger.euler.problems11to20.*;
import be.inniger.euler.problems21to30.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AllProblemsTest {

  private static Stream<Arguments> solutionsAndProblems() {
    return Stream.of(
        arguments(233168, new Problem01()),
        arguments(4613732, new Problem02()),
        arguments(6857, new Problem03()),
        arguments(906609, new Problem04()),
        arguments(232792560, new Problem05()),
        arguments(25164150, new Problem06()),
        arguments(104743, new Problem07()),
        arguments(23514624000L, new Problem08()),
        arguments(31875000, new Problem09()),
        arguments(142913828922L, new Problem10()),
        arguments(70600674, new Problem11()),
        arguments(76576500, new Problem12()),
        arguments(5537376230L, new Problem13()),
        arguments(837799L, new Problem14()),
        arguments(137846528820L, new Problem15()),
        arguments(1366, new Problem16()),
        arguments(21124, new Problem17()),
        arguments(1074, new Problem18()),
        arguments(171, new Problem19()),
        arguments(648, new Problem20()),
        arguments(31626, new Problem21()),
        arguments(871198282, new Problem22()));
  }

  @DisplayName("Verify the solution to all the solved problems")
  @ParameterizedTest(name = "Problem {index}")
  @MethodSource("solutionsAndProblems")
  void testAllProblems(long solution, Problem problem) {
    assertEquals(solution, problem.solve());
  }
}
