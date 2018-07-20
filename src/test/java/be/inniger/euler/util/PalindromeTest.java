package be.inniger.euler.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static be.inniger.euler.util.Palindrome.isPalindrome;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromeTest {

  @Test
  public void emptyAndSingleElementListsArePalindrome() {
    assertTrue(isPalindrome(List.of()));
    assertTrue(isPalindrome(List.of(42)));
    assertTrue(isPalindrome(List.of('a')));
    assertTrue(isPalindrome(List.of("b")));
  }

  @Test
  public void canDetectPalindromeLists() {
    assertTrue(isPalindrome(List.of("a", "a")));
    assertTrue(isPalindrome(List.of("a", "b", "a")));
    assertTrue(isPalindrome(List.of("a", "b", "b", "a")));
    assertTrue(isPalindrome(List.of(42, 1, 42)));
    assertTrue(isPalindrome(List.of('c', 'd', 'e', 'd', 'c')));
    assertTrue(isPalindrome(List.of("b", "b", "b", "b")));
  }

  @Test
  public void canDetectNotPalindromeLists() {
    assertFalse(isPalindrome(List.of("a", "b")));
    assertFalse(isPalindrome(List.of("a", "a", "b")));
    assertFalse(isPalindrome(List.of("a", "b", "a", "b")));
    assertFalse(isPalindrome(List.of(42, 1, 41)));
    assertFalse(isPalindrome(List.of('c', 'e', 'd', 'd', 'c')));
    assertFalse(isPalindrome(List.of("b", "b", "b", "a")));
  }
}
