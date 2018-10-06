package be.inniger.euler.problems21to30;

import be.inniger.euler.Problem;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static be.inniger.euler.util.CollectionUtil.readProblemDataAndTransform;
import static java.util.stream.Collectors.toUnmodifiableList;

/*
 * Names scores
 *
 * Using names.txt (here "Problem22.txt"), a 46K text file containing over five-thousand first names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.
 * For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.
 * What is the total of all the name scores in the file?
 */
public class Problem22 implements Problem {

  @Override
  public long solve() {
    var names = readNames();

    return IntStream.range(0, names.size())
        .map(i -> getScore(names, i))
        .sum();
  }

  private List<String> readNames() {
    return readProblemDataAndTransform(this, lines ->
        lines.flatMap(line ->
            Stream.of(line.split(",")))
            .map(name -> name.substring(1, name.length() - 1)) // Strip the quotes
            .sorted()
            .collect(toUnmodifiableList()));
  }

  private int getScore(List<String> names, int index) {
    var position = index + 1; // Indexing is 1-based in the problem description
    var alphabeticalValue = names.get(index).chars().map(c -> c - 'A' + 1).sum();

    return position * alphabeticalValue;
  }
}
