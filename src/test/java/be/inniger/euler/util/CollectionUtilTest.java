package be.inniger.euler.util;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static be.inniger.euler.util.CollectionUtil.getSingleElement;
import static be.inniger.euler.util.CollectionUtil.lastIndex;
import static be.inniger.euler.util.CollectionUtil.readProblemDataAndTransform;
import static be.inniger.euler.util.CollectionUtil.reverseStream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toUnmodifiableList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CollectionUtilTest {

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

  @Test
  public void canGetSingleElement() {
    assertEquals(42, getSingleElement(List.of(42)).intValue());
    assertEquals("Hello", getSingleElement(Set.of("Hello")));
  }

  @Test
  public void throwsOnSingleElementOfNullCollection() {
    assertThrows(IllegalArgumentException.class,
        () -> getSingleElement(null));
  }

  @Test
  public void throwsOnSingleElementOfNotSizedOneCollection() {
    assertThrows(IllegalArgumentException.class,
        () -> getSingleElement(List.of()));
  }

  @Test
  public void canLastIndex() {
    assertEquals(-1, lastIndex(List.of()));
    assertEquals(0, lastIndex(List.of(42)));
    assertEquals(1, lastIndex(List.of("Hello", "World")));
  }

  @Test
  public void throwsOnLastIndexOfNullList() {
    assertThrows(IllegalArgumentException.class,
        () -> lastIndex(null));
  }
}
