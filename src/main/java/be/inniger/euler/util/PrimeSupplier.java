package be.inniger.euler.util;

import java.util.stream.IntStream;

@FunctionalInterface
public interface PrimeSupplier {

  int nextPrime();

  default IntStream asStream() {
    return IntStream.iterate(nextPrime(), __ -> nextPrime());
  }

}
