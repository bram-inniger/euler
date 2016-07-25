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

package be.inniger.euler.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.jetbrains.annotations.NotNull;

/**
 * Place to drop miscellaneous utility methods.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Misc {

  /**
   * Read the words of a file into a List
   *
   * @return All of the words in the file in List form
   * @throws FileNotFoundException Should the file be missing / unreadable
   */
  @NotNull
  public static List<String> readWords(@NotNull File file, @NotNull String delimiter) throws FileNotFoundException {
    List<String> names = new ArrayList<>();
    Scanner scanner = new Scanner(file);
    scanner.useDelimiter(delimiter);

    while (scanner.hasNext()) {
      String quotedName = scanner.next(); // E.g. "MARI"
      String name = quotedName.substring(1, quotedName.length() - 1); // E.g. MARI (without the quotes)
      names.add(name);
    }

    return names;
  }
}
