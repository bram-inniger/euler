# Project Euler solutions repository

## About

A place for me to conveniently story my Project Euler solutions.
Serves also to further solidify mastery of git, java 8, maven...

## How to run

Each and every problem has its own class file containing the code how to solve it.
Every time a test file accompanies the class containing the actual solution, and validating the code.

Furthermore a nicely shaded JAR gets created on build, allowing to solve just a specific problem.

    $ git clone git@github.com:bram-inniger/euler.git Euler
    $ cd Euler
    $ mvn package -DskipTests # YOLO
    $ java -jar target/euler.jar 1

## Known issues

1. Only Euler problems solved and uploaded by me can be solved by this tool...
2. The input argument has the be _exactly_ a single integer specifying the problem number.

## License

GPLv3. See [LICENSE](LICENSE).

_Copyright (C) 2016 Bram Inniger_