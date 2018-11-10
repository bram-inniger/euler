package be.inniger.euler.problems21to30;

import be.inniger.euler.Problem;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

/*
 * Lexicographic permutations
 *
 * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:
 * 012 021 102 120 201 210
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
public class Problem24 implements Problem {

  // -1 as the problem specification is 1-indexed but Java's collections are 0-indexed
  private static final int INDEX = 1_000_000 - 1;
  private static final List<?> DIGITS = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

  // FIXME -- improve performance instead of brute-forcing
  @Override
  public long solve() {
    List<String> permutations = getPermutations(DIGITS)
        .stream()
        .sorted()
        .collect(toList());

    return Long.parseLong(permutations.get(INDEX));
  }

  private List<String> getPermutations(List<?> list) {
    if (list.isEmpty()) {
      return List.of("");
    }

    return IntStream.range(0, list.size())
        .mapToObj(i -> new ListPick<>(list, i))
        .flatMap(listPick -> getPermutations(listPick.remainder).stream()
            .map(permutation -> "" + listPick.pick + permutation))
        .collect(toUnmodifiableList());
  }

  private static class ListPick<T> {
    private T pick;
    private List<T> remainder;

    private ListPick(List<T> list, int toPick) {
      this.pick = list.get(toPick);
      this.remainder = IntStream.range(0, list.size())
          .filter(i -> i != toPick)
          .mapToObj(list::get)
          .collect(toUnmodifiableList());
    }
  }
}
