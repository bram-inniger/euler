package be.inniger.euler.value;

import be.inniger.euler.util.PrimeSupplier;
import be.inniger.euler.util.UnboundPrimeSupplier;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static be.inniger.euler.util.Math.abs;
import static be.inniger.euler.util.Math.pow;
import static be.inniger.euler.value.Factor.factor;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toUnmodifiableMap;

// TODO add description and test
public final class FactorizedInteger {

  private final int value;
  private final int absValue;
  private final Map<Integer, Factor> factors;

  private FactorizedInteger(int value, PrimeSupplier primes) {
    this.value = value;
    this.absValue = abs(value);
    this.factors = primes.asStream()
        .takeWhile(prime -> prime <= absValue)
        .mapToObj(this::getFactor)
        .flatMap(Optional::stream)
        .collect(toUnmodifiableMap(
            Factor::getPrime,
            identity()));
  }

  public static FactorizedInteger factorizedInteger(int value) {
    return new FactorizedInteger(value, UnboundPrimeSupplier.newInstance());
  }

  private Optional<Factor> getFactor(int prime) {
    // Calculate how many times a given prime "fits" in a number, 
    // the first time "nr times it fits + 1" is no fit, the answer is found!
    var exponent = absValue % prime != 0 ?
        0 :
        IntStream.iterate(1, be.inniger.euler.util.Math::inc)
            .dropWhile(i -> absValue % pow(prime, i + 1) == 0)
            .findFirst()
            .orElseThrow();
    return exponent != 0 ?
        Optional.of(factor(prime, exponent)) :
        Optional.empty();
  }

  public Stream<Factor> getFactors() {
    return factors.values().stream();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FactorizedInteger that = (FactorizedInteger) o;
    return value == that.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "FactorizedInteger{" +
        "value=" + value +
        ", factors=" + factors +
        '}';
  }

}
