package be.inniger.euler.problems11to20;

import be.inniger.euler.Problem;

import java.util.List;

import static be.inniger.euler.problems11to20.Problem17.EnglishNumber.englishNumber;

/*
 * Number letter counts
 *
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.
 */
public class Problem17 implements Problem {

  @Override
  public long solve() {
    var digits = List.of(
        englishNumber(""),
        englishNumber("one"),
        englishNumber("two"),
        englishNumber("three"),
        englishNumber("four"),
        englishNumber("five"),
        englishNumber("six"),
        englishNumber("seven"),
        englishNumber("eight"),
        englishNumber("nine"));
    var exceptions = List.of(
        englishNumber("ten"),
        englishNumber("eleven"),
        englishNumber("twelve"),
        englishNumber("thirteen"),
        englishNumber("fourteen"),
        englishNumber("fifteen"),
        englishNumber("sixteen"),
        englishNumber("seventeen"),
        englishNumber("eighteen"),
        englishNumber("nineteen"));
    var tens = List.of(
        englishNumber("twenty"),
        englishNumber("thirty"),
        englishNumber("forty"),
        englishNumber("fifty"),
        englishNumber("sixty"),
        englishNumber("seventy"),
        englishNumber("eighty"),
        englishNumber("ninety"));
    var hundred = "hundred".length();
    var and = "and".length();
    var thousand = ("one" + "thousand").length();

    var oneToNine = digits.stream()
        .mapToInt(EnglishNumber::getLength)
        .sum();
    var tenToNineteen = exceptions.stream()
        .mapToInt(EnglishNumber::getLength)
        .sum();
    var twentyToNinetyNine = tens.stream()
        .mapToInt(EnglishNumber::getLength)
        .flatMap(tensLength -> digits.stream().mapToInt(EnglishNumber::getLength).map(digitsLength -> tensLength + digitsLength))
        .sum();

    var oneToNinetyNine = oneToNine + tenToNineteen + twentyToNinetyNine;
    var oneToNineHundredNinetyNine = digits.stream()
        .mapToInt(digit -> digit.isExistAsPrefix() ?
            100 * (digit.getLength() + hundred) + 99 * and :
            0)
        .map(hundredAnd -> hundredAnd + oneToNinetyNine)
        .sum();

    return oneToNineHundredNinetyNine + thousand;
  }

  static class EnglishNumber {

    private final int length;
    private final boolean existAsPrefix;

    public EnglishNumber(int length, boolean existAsPrefix) {
      this.length = length;
      this.existAsPrefix = existAsPrefix;
    }

    static EnglishNumber englishNumber(String text) {
      return new EnglishNumber(text.length(), !text.isBlank());
    }

    public int getLength() {
      return length;
    }

    public boolean isExistAsPrefix() {
      return existAsPrefix;
    }
  }
}
