package be.inniger.euler.problems11to20;

import be.inniger.euler.Problem;

import java.time.LocalDate;
import java.util.stream.Stream;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;

/*
 * Counting Sundays
 *
 * You are given the following information, but you may prefer to do some research for yourself.
 * * 1 Jan 1900 was a Monday.
 * * Thirty days has September,
 * * April, June and November.
 * * All the rest have thirty-one,
 * * Saving February alone,
 * * Which has twenty-eight, rain or shine.
 * * And on leap years, twenty-nine.
 * * A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */
public class Problem19 implements Problem {

  private static final LocalDate BEGIN = LocalDate.of(1901, JANUARY, 1);
  private static final LocalDate END = LocalDate.of(2000, DECEMBER, 31);

  @Override
  public long solve() {
    return Stream.iterate(BEGIN, date -> date.isBefore(END), date -> date.plusMonths(1))
        .map(LocalDate::getDayOfWeek)
        .filter(SUNDAY::equals)
        .count();
  }
}
