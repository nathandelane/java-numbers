package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

public class TestRationalEquals {
  
  @Test
  public void testCompareRationalAndBigInteger() {
    final BigInteger n = BigInteger.ONE;
    final Rational r = Rational.ONE;
    
    assertTrue("Rational.ONE is not equal to BigInteger.ONE", r.equals(n));
  }
  
  @Test
  public void testCompareRationalAndBigIntegerWholeNumber() {
    final BigInteger n = BigInteger.valueOf(5);
    final Rational r = new Rational(25, 5);
    
    assertTrue(String.format("Rational (%1$s) and BigInteger (%2$s) are not equal, but should be.", r, n), r.equals(n));
  }

}
