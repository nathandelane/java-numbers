package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

public class TestRationalConstructors {
  
  @Test
  public void testBigIntegerConstructor() {
    final BigInteger numerator = BigInteger.valueOf(1L);
    final BigInteger denominator = BigInteger.valueOf(1L);
    final Rational rational = new Rational(numerator, denominator);
    
    assertTrue(String.format("%1$s/%2$s == %3$s",  numerator, denominator, rational), rational.bigIntegerValue().equals(BigInteger.ONE));
  }

}
