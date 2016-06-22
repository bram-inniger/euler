/*
 * Project Euler solution repository
 * Copyright (C) 2016 Bram Inniger
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package be.inniger.euler.problems11to20;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * You are given the following information, but you may prefer to do some research for yourself.
 * 1 Jan 1900 was a Monday.
 * Thirty days has September,
 * April, June and November.
 * All the rest have thirty-one,
 * Saving February alone,
 * Which has twenty-eight, rain or shine.
 * And on leap years, twenty-nine.
 * A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem19 implements Problem {

  private static final LocalDate FROM = LocalDate.of(1901, Month.JANUARY, 1);
  private static final LocalDate TO = LocalDate.of(2000, Month.DECEMBER, 31);

  /**
   * The expected solution would involve trivial yet tedious calculations.
   * But why re-invent the wheel? Java 8's DateTime API will do the trick easily.
   *
   * @return Number of Sundays on the first of the month in the years 1901 up to 2000
   */
  @NotNull
  @Override
  public String solve() {
    LocalDate date = FROM;
    int nrSundays = 0;

    while (date.isBefore(TO)) {
      if (DayOfWeek.SUNDAY.equals(date.getDayOfWeek())) {
        nrSundays++;
      }

      date = date.plusMonths(1);
    }

    return "" + nrSundays;
  }
}
