package be.inniger.euler.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnboundPrimeSupplierTest {

  @Test
  void canGetNextPrime() {
    UnboundPrimeSupplier supplier = UnboundPrimeSupplier.newInstance();

    assertEquals(2, supplier.nextPrime());
    assertEquals(3, supplier.nextPrime());
    assertEquals(5, supplier.nextPrime());
    assertEquals(7, supplier.nextPrime());
  }

  @Test
  void suppliersAreDistinct() {
    UnboundPrimeSupplier firstSupplier = UnboundPrimeSupplier.newInstance();
    UnboundPrimeSupplier secondSupplier = UnboundPrimeSupplier.newInstance();

    assertEquals(2, firstSupplier.nextPrime());
    assertEquals(2, secondSupplier.nextPrime());
    assertEquals(3, secondSupplier.nextPrime());
    assertEquals(3, firstSupplier.nextPrime());
  }
}
