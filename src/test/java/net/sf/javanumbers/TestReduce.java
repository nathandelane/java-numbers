package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestReduce {

  @Test
  public void testReduce() {
    final Rational r = Rational.valueOf(0.125);
    final Rational reducedR = r.reduce();
    
    assertTrue(reducedR.equals(new Rational(1, 8)));
  }
  
}
