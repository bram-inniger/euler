package be.inniger.euler.util;

import java.util.List;

import static be.inniger.euler.util.CollectionUtil.lastIndex;

public final class Palindrome {

  private Palindrome() {
    throw new IllegalStateException("Utility class constructor should never be called!");
  }

  public static boolean isPalindrome(List<?> list) {
    if (list.size() <= 1) {
      return true;
    }

    if (!list.get(0).equals(list.get(lastIndex(list)))) {
      return false;
    }

    return isPalindrome(list.subList(1, lastIndex(list)));
  }
}
