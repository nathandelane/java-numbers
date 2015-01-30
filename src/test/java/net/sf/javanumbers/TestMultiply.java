package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestMultiply {
  
  @Test
  public void testBasicallyMultiply() {
    final Rational r1 = new Rational(1, 2);
    final Rational c1 = new Rational(1, 4);
    
    assertTrue(r1.multiply(r1).equals(c1));
  }

}
