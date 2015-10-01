package net.sf.javanumbers.internal;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Simple tuple container.
 * @author nathanlane
 *
 */
public final class Tuple {
  
  private final List<BigInteger> values;
  
  public Tuple(BigInteger ... values) {
    this.values = Arrays.asList(values);
  }
  
  public BigInteger get(int index) {
    return values.get(index);
  }
  
  public BigInteger getFirst() {
    return values.get(0);
  }
  
  public BigInteger getLast() {
    return values.get(values.size() - 1);
  }
  
  @Override
  public String toString() {
    return values.toString();
  }
  
  @Override
  public int hashCode() {
    return new HashSet<BigInteger>(values).hashCode();
  }
  
  @Override
  public boolean equals(Object other) {
    if (other instanceof Tuple) {
      final Set<BigInteger> left = new HashSet<BigInteger>(values);
      final Set<BigInteger> right = new HashSet<BigInteger>(((Tuple) other).values);
      
      return left.containsAll(right) && left.size() == right.size();
    }
    
    return false;
  }
  
}