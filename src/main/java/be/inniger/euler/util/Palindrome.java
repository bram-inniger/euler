package be.inniger.euler.util;

import java.util.List;

public final class Palindrome {

  private Palindrome() {
    throw new IllegalStateException("Utility class constructor should never be called!");
  }

  public static boolean isPalindrome(List<?> list) {
    if (list.size() <= 1) {
      return true;
    }

    if (!list.get(0).equals(list.get(list.size() - 1))) {
      return false;
    }

    return isPalindrome(list.subList(1, list.size() - 1));
  }
}
