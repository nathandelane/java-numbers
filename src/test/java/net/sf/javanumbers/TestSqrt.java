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
  
  @Test
  public void testSqrtPotentiallyDestructive() {
    final Rational r = new Rational(1, 5);
    final double sqrtOfOneFifth = Math.sqrt(1.0 / 5.0);
    final double sqrtOfR = r.sqrt().doubleValue();
    
    assertTrue(sqrtOfR == Rational.valueOf(sqrtOfOneFifth).doubleValue());
  }

}
