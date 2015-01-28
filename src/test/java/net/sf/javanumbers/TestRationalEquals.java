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

}
