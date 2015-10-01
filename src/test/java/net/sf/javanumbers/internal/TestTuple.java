package net.sf.javanumbers.internal;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import net.sf.javanumbers.java.BigIntegerFactory;

public class TestTuple {

  @Test
  public void test() {
    final Tuple left = new Tuple(BigIntegerFactory.create(1), BigIntegerFactory.create(2));
    final Tuple right = new Tuple(BigIntegerFactory.create(2), BigIntegerFactory.create(1));
    
    assertTrue(left.equals(right));
  }
  
}
