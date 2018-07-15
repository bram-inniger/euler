# Project Euler solutions repository

## IMPORTANT

This repo is in the process of being rewritten to much nicer functional/declarative code.
Furthermore the goal will be to keep both this repository an its sister repo (euler-kotlin) in sync. // TODO add link
Code written in 2018 is the new nicer one. Code written in 2016 probably does not reflect my current coding skill an practices.
The 2016 code at the moment has been deleted but can easily be retrieved by going into the repo history. // TODO add link to older state

## About

A place for me to conveniently story my Project Euler solutions.
Serves also to further practice git, java 8, maven...

## How to read

Each and every problem has its own class file containing the code how to solve it.
Every time a test file accompanies the class containing the actual solution, and validating the code.

## Clean-up TODO
* Cleanly extract reading from disk logic
* Extract proper value classes in a value-package, link this doc: https://docs.oracle.com/javase/8/docs/api/java/lang/doc-files/ValueBased.html
* Clean up the util classes, maybe also add note on what makes a util class
* Extract code duplication (Factor, getFrequency) + tests
* Add java.lang.Math.* proxy methods in own Math class
* Move EratosthenesSieve into Math, hiding away the details behind a static "getPrimesUpUntil(int)" method
* Upgrade to JUnit 5
* Bundle all tests into 1 class (per package?)
* Solve more problems (or reclaim the old ones and polish them back up)

## License

GPLv3. See [LICENSE](LICENSE).

_Copyright (C) 2016 Bram Inniger_
