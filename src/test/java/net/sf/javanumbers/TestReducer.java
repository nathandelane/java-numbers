package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import net.sf.javanumbers.java.BigDecimalFactory;

/**
 * Tests for {@link Reducer}.
 * @author nathanlane
 *
 */
public class TestReducer {
  
  private static final int LOAD_TEST_DECIMAL_VALUE_LENGTH = 10;
  private static final long BENCHMARK_TIME_10_000_DECIMALS = 15; // That's 15 milliseconds
  
  @Test
  public void testReduceFloatingPointValue() {
    final String value = "0.125";
    final Rational r = Reducer.reduceFloatingPointValue(BigDecimalFactory.create(value));
    
    assertTrue(r != null && Rational.compareValues(r, new Rational(1, 8)));
  }
  
  @Test
  public void loadTestReducer10_000Decimals() {
    final Set<BigDecimal> randomDecimals = generateRandomDecimals(10000);
    final long startTime = Calendar.getInstance().getTimeInMillis();
    
    for (BigDecimal decimal : randomDecimals) {
      Reducer.reduceFloatingPointValue(decimal);
    }
    
    final long endTime = Calendar.getInstance().getTimeInMillis();
    final long totalTime = (endTime - startTime);
    
    assertTrue(String.format("Total time took longer than benchmark of %s: %s", BENCHMARK_TIME_10_000_DECIMALS, totalTime), totalTime <= BENCHMARK_TIME_10_000_DECIMALS);
  }
  
  private static Set<BigDecimal> generateRandomDecimals(final int numberOfDecimals) throws IllegalArgumentException {
    if (numberOfDecimals <= 0) {
      throw new IllegalArgumentException("Number of decimals must be greater than zero.");
    }
    
    final char[] digits = "0123456789".toCharArray();
    final Set<BigDecimal> randomDecimals = new HashSet<BigDecimal>();
    
    for (int x = 0; x < numberOfDecimals; x++) {
      final StringBuilder sb = new StringBuilder("0.");
      final Random r = new Random(Calendar.getInstance().getTimeInMillis());
      
      for (int position = 0; position < LOAD_TEST_DECIMAL_VALUE_LENGTH; position++) {
        sb.append(digits[r.nextInt(10)]);
      }
      
      randomDecimals.add(BigDecimalFactory.create(sb.toString()));
    }
    
    return randomDecimals;
  }

}
