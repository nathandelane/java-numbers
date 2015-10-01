package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import net.sf.javanumbers.internal.Tuple;
import net.sf.javanumbers.java.BigIntegerFactory;

public class TestFactors {
  
  @Test
  public void testGreatestCommonFactor() {
    final Set<Tuple> pairs = generateTuples();
    
    for (Tuple nextTuple : pairs) {
      final BigInteger gcf = Factors.greatestCommonFactor(nextTuple.getFirst(), nextTuple.get(1));
      
      assertTrue(String.format("GCD of %s and %s is not %s: %s", nextTuple.getFirst(), nextTuple.get(1), nextTuple.getLast(), gcf), gcf.compareTo(nextTuple.getLast()) == 0);
    }
  }
  
  /**
   * Generates a list of tuples representing &lt;first-factor, second-factor, greatest-common-factor&gt;.
   * @return list of {@link Tuple} as described
   */
  private Set<Tuple> generateTuples() {
    final Set<Tuple> pairs = new HashSet<Tuple>();
    
    for (int x = 1; x <= 12; x++) {
      for (int y = 1; y <= 12; y++) {
        if (x % y == 0) {
          pairs.add(new Tuple(BigIntegerFactory.create(x), BigIntegerFactory.create(y), BigIntegerFactory.create(x < y ? x : y)));
        }
      }
    }
    
    return pairs;
  }
  
}
