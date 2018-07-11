package be.inniger.euler.util;

import org.junit.Test;

import static be.inniger.euler.util.Palindrome.isPalindrome;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PalindromeTest {

  @Test
  public void emptyAndSingleElementListsArePalindrome() {
    assertTrue(isPalindrome(emptyList()));
    assertTrue(isPalindrome(singletonList(42)));
    assertTrue(isPalindrome(singletonList('a')));
    assertTrue(isPalindrome(singletonList("b")));
  }

  @Test
  public void canDetectPalindromeLists() {
    assertTrue(isPalindrome(asList("a", "a")));
    assertTrue(isPalindrome(asList("a", "b", "a")));
    assertTrue(isPalindrome(asList("a", "b", "b", "a")));
    assertTrue(isPalindrome(asList(42, 1, 42)));
    assertTrue(isPalindrome(asList('c', 'd', 'e', 'd', 'c')));
    assertTrue(isPalindrome(asList("b", "b", "b", "b")));
  }

  @Test
  public void canDetectNotPalindromeLists() {
    assertFalse(isPalindrome(asList("a", "b")));
    assertFalse(isPalindrome(asList("a", "a", "b")));
    assertFalse(isPalindrome(asList("a", "b", "a", "b")));
    assertFalse(isPalindrome(asList(42, 1, 41)));
    assertFalse(isPalindrome(asList('c', 'e', 'd', 'd', 'c')));
    assertFalse(isPalindrome(asList("b", "b", "b", "a")));
  }
}
