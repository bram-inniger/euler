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

package be.inniger.euler;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import com.google.common.reflect.ClassPath;

/**
 * Main class of the project, invoke to solve a specific problem by problem nr.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Main {

  private static final Logger log = LogManager.getLogger(Main.class);
  private static final int FAIL_CODE = 1;

  /**
   * Main entry point.
   *
   * @param args A single integer, signifying the problem to be solved
   */
  public static void main(@NotNull String... args) throws Exception {
    try {
      Map<String, ClassPath.ClassInfo> availableProblems = getAvailableProblems();
      int problemNr = getProblemNumber(args);
      String problemName = "Problem" + (problemNr < 10 ? "0" : "") + problemNr;

      if (!availableProblems.containsKey(problemName)) {
        throw new IllegalArgumentException("Problem not found: " + problemName);
      }

      Class<?> problemClass = Class.forName(availableProblems.get(problemName).getName());
      Problem problem = (Problem) problemClass.newInstance();

      log.info("The solution is: \"{}\"", problem.solve());
    }
    catch (IllegalArgumentException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
      log.error("Caught \"{}\" with message \"{}\"", e.getClass(), e.getMessage());
      exitFail();
    }
  }

  /**
   * Get all of the available problems to solve.
   *
   * @return A Map of all available problems, with their single classname as the key
   */
  @NotNull
  private static Map<String, ClassPath.ClassInfo> getAvailableProblems() {
    try {
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      return ClassPath.from(loader).getTopLevelClasses()
          .stream()
          .filter(info -> info.getName().startsWith("be.inniger.euler.problems"))
          .collect(Collectors.toMap(info -> {
            String fullName = info.getName();
            return fullName.substring(fullName.lastIndexOf('.') + 1);
          }, Function.identity()));
    }
    catch (IOException e) {
      log.error("Failed to retrieve the list of problems: \"{}\"", e.getMessage());
      exitFail();
    }

    return Collections.emptyMap(); // Will not be reached anyway
  }

  /**
   * Validate the inputs and extract the problem nr.
   *
   * @param args A single integer, signifying the problem to be solved
   * @return The number of the to be solved problem
   * @throws IllegalArgumentException The input should be a single integer
   */
  private static int getProblemNumber(@NotNull String... args) throws IllegalArgumentException {
    if (args.length != 1) {
      throw new IllegalArgumentException("Expected 1 and exactly 1 argument! ");
    }

    return Integer.parseInt(args[0]);
  }

  /**
   * Exit the JVM with the fail error code, something went horrible wrong!
   */
  private static void exitFail() {
    log.error("Exiting now...");
    System.exit(FAIL_CODE);
  }
}
