package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestSqrt {
  
  @Test
  public void testSqrt() {
    final Rational r = new Rational(4, 1);
    final Rational sqrtR = r.sqrt();
    
    assertTrue(sqrtR.equals(2));
  }

}
