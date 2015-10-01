package net.sf.javanumbers;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * <p>
 * Copyright (C) 2013 Nathan Lane, nathandelane &lt;nathan.david.lane@gmail.com&gt;
 * </p>
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * </p>
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * </p>
 * <p>
 * This class can be used to work with rational numbers, or fractions, in a non-destructive deterministic manner. It is
 * based on {@link BigInteger} and {@link BigDecimal}.
 * </p>
 * 
 * Internal class used to reduce {@link Rational} values.
 * 
 * @author nathandelane &lt;nathan.david.lane@gmail.com&gt;
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
  static Rational reduceFloatingPointValue(BigDecimal f) {
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
