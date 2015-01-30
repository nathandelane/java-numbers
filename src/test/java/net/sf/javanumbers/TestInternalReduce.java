package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class TestInternalReduce {

  @Test
  public void testInternalReduce() {
    final Rational r = new Rational(1, 1);
    final Rational rReduced = r.reduce(BigDecimal.valueOf(0.125));
    
    assertTrue(rReduced != null);
  }
  
}
