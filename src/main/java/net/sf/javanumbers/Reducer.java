package net.sf.javanumbers;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Internal class used to reduce {@link Rational} values.
 * 
 * @author nathanlane
 */
abstract class Reducer {

  /**
   * Reduce a {@link Rational} naturally, for example 2/4 to 1/2.
   * @param r
   * @return
   */
  public static Rational reduce(Rational r) {
    // Reduce to zero.
    if (r.getNumerator().equals(BigDecimal.ZERO)) {
      return Rational.ZERO;
    }
    
    return reduceToRational(reduceByFloatingPointPlaceValue(r));
  }
  
  /**
   * Reduces a Rational of a decimal point reduction into an improper reduced Rational.
   * @param r
   * @return
   */
  static Rational reduceToRational(Rational r) {
    final BigDecimal rNumerator = r.getNumerator();
    final BigDecimal rDenominator = r.getDenominator();
    
    if (rNumerator.scale() > 0 || rDenominator.scale() > 0) {
      throw new IllegalStateException("Expected whole number numerator and denominator.");
    }
    
    final BigDecimal gcf = new BigDecimal(Factors.greatestCommonFactor(rNumerator.toBigInteger().abs(), rDenominator.toBigInteger().abs()));
    
    return new Rational(rNumerator.divide(gcf), rDenominator.divide(gcf));
  }
  
  /**
   * Attempt to reduce a floating point value.
   * @param r
   * @return
   */
  static Rational reduceByFloatingPointPlaceValue(Rational r) {
    return reduceFloatingPointValue(r.getNumerator())
      .divide(reduceFloatingPointValue(r.getDenominator()));
  }
  
  /**
   * Reduce by floating point value.
   * @param f
   * @return
   */
  private static Rational reduceFloatingPointValue(BigDecimal f) {
    final String[] rationalParts = f.toString().split("[\\.]{1}");
    
    if (rationalParts.length == 2) {
      String beforeDecimal = rationalParts[0];
      String afterDecimal = rationalParts[1];
      
      if (beforeDecimal.indexOf("-") > -1 && new BigDecimal(beforeDecimal).compareTo(BigDecimal.ZERO) >= 0) {
        beforeDecimal = beforeDecimal.replace("-", "");
        afterDecimal = "-" + afterDecimal;
      }
      
      final StringBuilder dStr = new StringBuilder("1");
    
      for (int i = 0; i < afterDecimal.replace("-", "").length(); i++) {
        dStr.append("0");
      }
  
      final BigInteger d = new BigInteger(dStr.toString());
      final BigInteger n = new BigInteger(afterDecimal).add(new BigInteger(beforeDecimal).multiply(d));
      
      return new Rational(n, d);
    }
    
    return new Rational(f, BigDecimal.ONE);

  }

}
