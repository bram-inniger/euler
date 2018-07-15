package be.inniger.euler.util;

import org.junit.Test;

import java.util.List;

import static be.inniger.euler.util.StreamUtil.readProblemDataAndTransform;
import static be.inniger.euler.util.StreamUtil.reverseStream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toUnmodifiableList;
import static org.junit.Assert.assertEquals;

public class StreamUtilTest {

  @Test
  public void canReverse() {
    assertEquals(List.of(), reverseStream(List.of()).collect(toUnmodifiableList()));
    assertEquals(List.of(1), reverseStream(List.of(1)).collect(toUnmodifiableList()));
    assertEquals(List.of(2, 1), reverseStream(List.of(1, 2)).collect(toUnmodifiableList()));
    assertEquals(List.of(3, 2, 1), reverseStream(List.of(1, 2, 3)).collect(toUnmodifiableList()));
    assertEquals(List.of('d', 'c', 'b', 'a'), reverseStream(List.of('a', 'b', 'c', 'd')).collect(toUnmodifiableList()));
    assertEquals(List.of(false, true), reverseStream(List.of(true, false)).collect(toUnmodifiableList()));
    assertEquals(List.of("right", "left"), reverseStream(List.of("left", "right")).collect(toUnmodifiableList()));
  }

  @Test
  public void canReadProblemDataAndTransform() {
    assertEquals("Hello,World", readProblemDataAndTransform("TestProblem", lines -> lines.collect(joining(","))));
  }
}
