package net.sf.javanumbers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.regex.Pattern;

import org.javatuples.Pair;

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
 * </p>
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see &lt;http://www.gnu.org/licenses/&gt;.
 * </p>
 * <p>
 * This class can be used to work with rational numbers, or fractions, in a non-destructive deterministic manner. It is
 * based on {@link BigInteger} and {@link BigDecimal}.
 * </p>
 *
 * @author nathandelane &lt;nathan.david.lane@gmail.com&gt;
 *
 */
public class Rational extends Number implements Comparable<Rational> {

  public static final Rational ONE = new Rational(1, 1);
  public static final Rational ZERO = new Rational(0, 1);

  private static final long serialVersionUID = -6831475929480288445L;
  
  private static final BigDecimal BIG_DECIMAL_NEGATIVE_ONE = new BigDecimal("-1.0");
  private static final Pattern RATIONAL_PATTERN = Pattern.compile("^([\\-]{0,1}[\\d]+)/([\\-]{0,1}[\\d]+)$");
  private static final Pattern FLOATING_POINT_PATTERN = Pattern.compile("[\\-]{0,1}([\\d]*)(\\.([\\d]+)){0,1}$");
  private static final int DEFAULT_BIG_DECIMAL_SCALE = 32;
  private static final RoundingMode DEFAULT_BIG_DECIMAL_ROUNDING_MODE = RoundingMode.HALF_UP;

  private final BigDecimal numerator;
  private final BigDecimal denominator;
  private final boolean isRational;
  private final boolean isComplex;

  private int scale;
  private RoundingMode roundingMode;
  
  public Rational(BigDecimal n, BigDecimal d) {
    if (n == null || d == null) {
      throw new NullPointerException("Arguments may not be null.");
    }
    
    final Pair<BigDecimal, BigDecimal> negNorm = Rational.normalizeNegative(n, d);
    
    numerator = negNorm.getValue0();
    denominator = negNorm.getValue1();

    assertNotDivisionByZero(denominator);
    
    isRational = true;
    isComplex = false;
    scale = Rational.DEFAULT_BIG_DECIMAL_SCALE;
    roundingMode = Rational.DEFAULT_BIG_DECIMAL_ROUNDING_MODE;
  }
  
  public Rational(BigInteger n, BigInteger d) {
    this(new BigDecimal(n), new BigDecimal(d));
  }
  
  public Rational(long n, long d) {
    this(new BigDecimal(n), new BigDecimal(d));
  }
  
  public Rational(int n, int d) {
    this(new BigDecimal(n), new BigDecimal(d));
  }
  
  public Rational(double n, double d) {
    this(new BigDecimal(n), new BigDecimal(d));
  }
  
  public Rational(float n, float d) {
    this(new BigDecimal(n), new BigDecimal(d));
  }
  
  public Rational(Rational r) {
    if (r == null) {
      throw new NullPointerException("Argument may not be null.");
    }
    
    numerator = r.numerator;
    denominator = r.denominator;
    isRational = r.isRational;
    isComplex = r.isComplex;
    scale = r.scale;
    roundingMode = r.roundingMode;
  }
  
  /**
   * Private constructor used internally by Rational.
   * @param numerator the numerator <i>n</i> of this rational n/d
   * @param denominator the denominator <i>d</i> of this rational n/d
   * @param isRational whether or not this rational is rational, usually <code>true</code>
   * @param isComplex whether or not this rational is complex
   * @param scale the {@link BigDecimal} scale at which to treat this rational
   * @param roundingMode the {@link RoundingMode} at which to treat this rational
   */
  private Rational(BigDecimal numerator, BigDecimal denominator, boolean isRational, boolean isComplex, int scale, RoundingMode roundingMode) {
    this.numerator = numerator;
    this.denominator = denominator;
    this.isRational = isRational;
    this.isComplex = isComplex;
    this.scale = scale;
    this.roundingMode = roundingMode;
  }
  
  /**
   * Package local method to get the numerator of this Rational.
   * @return {@link BigDecimal} numerator value
   */
  BigDecimal getNumerator() {
    return new BigDecimal(numerator.toString());
  }
  
  /**
   * Package local method to get the denominator of this Rational.
   * @return {@link BigDecimal} denominator value
   */
  BigDecimal getDenominator() {
    return new BigDecimal(denominator.toString());
  }
  
  /**
   * Not currently used, such that all Rationals are rational.
   * @return whether this Rational is actually rational.
   */
  public boolean isRational() {
    return isRational;
  }
  
  /**
   * Not currently used, such that all Rationals are not complex.
   * @return whether this Rational is a complex number, meaning it has an imaginary part.
   */
  public boolean isComplex() {
    return isComplex;
  }
  
  /**
   * @return whether this Rational is less than zero.
   */
  public boolean isNegative() {
    return numerator.compareTo(BigDecimal.ZERO) < 0;
  }
  
  /**
   * @return the current scale for this Rational.
   */
  public int scale() {
    return scale;
  }
  
  /**
   * Sets the scale, or number of retained decimal places, for this Rational.
   * @param scale the number of decimal places retained for operations on this Rational.
   * @return A new Rational with the newly set scale.
   */
  public Rational setScale(int scale) {
    this.scale = scale;
    
    return new Rational(this);
  }
  
  /**
   * @return the current {@link RoundingMode} for this Rational.
   */
  public RoundingMode roundingMode() {
    return roundingMode;
  }
  
  /**
   * Sets the {@link RoundingMode} for the division operations of this Rational's numerator and denominator. 
   * @param roundingMode {@link RoundingMode} at which to treat this rational
   * @return A new Rational with the newly set rounding mode.
   */
  public Rational setRoundingMode(RoundingMode roundingMode) {
    final Rational newRational = new Rational(this);
    newRational.roundingMode = roundingMode;
    
    return newRational;
  }
  
  /**
   * Reduces this Rational to an integer-based rational, and attempts to reduce by division.
   * @return A new Rational reduced.
   */
  public Rational reduce() {
    return Reducer.reduce(this);
  }
  
  /**
   * Adds this to another Rational value, resulting in a single Rational value.
   * @param r {@link Rational} value
   * @return A new Rational containing the value of the sum of this and r.
   */
  @Destructive("Uses LCM of the denominators which must be converted to BigIntegers in order to operate.")
  public Rational add(Rational r) {
    Rational newThis = this.reduce();
    Rational otherR = r.reduce();
    
    if (newThis.denominator.equals(otherR.denominator)) {
      return new Rational(newThis.numerator.add(otherR.numerator), newThis.denominator);
    }
    
    if (newThis.denominator.equals(BigDecimal.ONE)) {
      newThis = new Rational(newThis.numerator.multiply(otherR.denominator), newThis.denominator.multiply(otherR.denominator));
    }
    
    final BigDecimal lcm = new BigDecimal(Multiples.leastCommonMultiple(newThis.denominator.toBigInteger(), otherR.denominator.toBigInteger()));
    final BigDecimal leftNumerator = newThis.numerator.multiply(lcm.divide(newThis.denominator));
    final BigDecimal rightNumerator = otherR.numerator.multiply(lcm.divide(otherR.denominator));
    
    return new Rational(leftNumerator.add(rightNumerator), lcm);
  }

  /**
   * Subtracts another Rational value from this, resulting in a single Rational value.
   * @param r {@link Rational} value
   * @return A new Rational containing the value of the difference of this and r.
   */
  public Rational subtract(Rational r) {
    return add(new Rational(r.numerator.multiply(Rational.BIG_DECIMAL_NEGATIVE_ONE), r.denominator));
  }

  /**
   * Multiplies this and another Rational value, resulting in a single Rational value.
   * @param r {@link Rational} value
   * @return A new Rational containing the value of the product of this and r.
   */
  public Rational multiply(Rational r) {
    return new Rational(
      this.numerator.multiply(r.numerator),
      this.denominator.multiply(r.denominator),
      (this.isRational && r.isRational),
      (this.isComplex && r.isComplex),
      Math.max(this.scale, r.scale),
      r.roundingMode
    );
  }

  /**
   * Divides this Rational by another Rational, resulting in a single Rational value.
   * @param r {@link Rational} value
   * @return a new Rational containing the value of the quotient of this and r.
   */
  public Rational divide(Rational r) {
    final Rational invertedR = invert(r);
    
    return multiply(invertedR);
  }
  
  /**
   * Returns the square root of this Rational.
   * @throws IllegalStateException When precision is lost in converting between BigDecimal and double.
   * @return the square root of this Rational as a new Rational.
   */
  @Destructive("This uses double values to find square roots, and throws an IllegalStateException if it can't, but it may still be destructive.")
  public Rational sqrt() throws IllegalStateException {
    final Rational r = reduce();
    
    if (new BigDecimal(r.numerator.doubleValue()).compareTo(r.numerator) == 0 &&
        new BigDecimal(r.denominator.doubleValue()).compareTo(r.denominator) == 0) {
      return new Rational(Math.sqrt(r.numerator.doubleValue()), Math.sqrt(r.denominator.doubleValue()));
    }
    
    throw new IllegalStateException("Cannot maintain precision of number in calculating sqaure roots. "
      + "This is a shortcoming of this library, because the numerator and denominator are converted to doubles first.");
  }
  
  /**
   * Effectively multiplies this Rational by -1.
   * @return the negation of this Rational as a new Rational.
   */
  public Rational negate() {
    return new Rational(numerator.multiply(BigDecimal.valueOf(-1.0)), denominator);
  }
  
  /**
   * Visible for unit testing, otherwise this is used internally.
   * Inverts a rational.
   * @param r {@link Rational} value
   * @return this Rational inverted or flipped as a new Rational.
   */
  public Rational invert(Rational r) {
    final BigDecimal n = r.denominator;
    final BigDecimal d = r.numerator;
    
    return new Rational(n, d);
  }
  
  @Override
  public int compareTo(Rational r) {
    final BigDecimal subtractionResult = reduce().subtract(r.reduce()).bigDecimalValue();
    
    return subtractionResult.compareTo(BigDecimal.ZERO);
  }
  
  @Destructive("This will truncate to the number of decimal places set by the scale the quotient of the numerator and the denominator.")
  public BigDecimal bigDecimalValue() {
    try {
      return numerator.divide(denominator);
    } catch (ArithmeticException e) {
      return numerator.divide(denominator, scale, roundingMode);
    }
  }
  
  @Destructive("This will truncate any decimal portion of the quotient of the numerator and the denominator.")
  public BigInteger bigIntegerValue() {
    try {
      return numerator.divide(denominator).toBigInteger();
    } catch (ArithmeticException e) {
      return numerator.divide(denominator, scale, roundingMode).toBigInteger();
    }
  }
  
  @Destructive("This will truncate any decimal portion to the limit of decimal places set by the JVM for double values.")
  @Override
  public double doubleValue() {
    try {
      return numerator.divide(denominator).doubleValue();
    } catch (ArithmeticException e) {
      return numerator.divide(denominator, scale, roundingMode).doubleValue();
    }
  }
  
  @Destructive("This will truncate any decimal portion to the limit of decimal places set by the JVM for float values.")
  @Override
  public float floatValue() {
    return numerator.divide(denominator, scale, roundingMode).floatValue();
  }
  
  @Destructive("This will truncate any decimal portion of the quotient of the numerator and the denominator.")
  @Override
  public int intValue() {
    try {
      return numerator.divide(denominator).intValue();
    } catch (ArithmeticException e) {
      return numerator.divide(denominator, scale, roundingMode).intValue();
    }
  }
  
  @Destructive("This will truncate any decimal portion of the quotient of the numerator and the denominator.")
  @Override
  public long longValue() {
    try {
      return numerator.divide(denominator).longValue();
    } catch (ArithmeticException e) {
      return numerator.divide(denominator, scale, roundingMode).longValue();
    }
  }
  
  /**
   * Ensures that the negative value is in the numerator if it is currently in the denominator, and that -x/-y 
   * is transformed into x/y.
   * @return a pair of values representing the numerator and denominator, with the negative number in the numerator, if necessary
   */
  static Pair<BigDecimal, BigDecimal> normalizeNegative(BigDecimal n, BigDecimal d) {
    if (d.compareTo(BigDecimal.ZERO) < 0 && n.compareTo(BigDecimal.ZERO) > 0) {
      return new Pair<BigDecimal, BigDecimal>(n.multiply(Rational.BIG_DECIMAL_NEGATIVE_ONE), d.multiply(Rational.BIG_DECIMAL_NEGATIVE_ONE));
    }
    
    return new Pair<BigDecimal, BigDecimal>(n, d);
  }
  
  /**
   * Compares two values for equality.
   * 
   * @param left
   * @param right
   * @return
   */
  static boolean compareValues(Rational left, Rational right) {
    final Rational reducedLeft = left.reduce();
    final Rational reducedRight = right.reduce();
    
    final int numeratorScale = Math.max(reducedLeft.numerator.scale(), reducedRight.numerator.scale());
    final int denominatorScale = Math.max(reducedLeft.denominator.scale(), reducedRight.denominator.scale());
    
    return (reducedLeft.numerator.setScale(numeratorScale).equals(reducedRight.numerator.setScale(numeratorScale)) && 
        reducedLeft.denominator.setScale(denominatorScale).equals(reducedRight.denominator.setScale(denominatorScale))) ||
        (reducedLeft.doubleValue() == reducedRight.doubleValue());
  }
  
  /**
   * If the denominator of a Rational is zero then throw an {@link ArithmeticExcetion}.
   * @param d
   */
  static void assertNotDivisionByZero(BigDecimal d) {
    if (d.equals(BigDecimal.ZERO)) {
      throw new ArithmeticException("Division by zero.");
    }
  }
  
  /**
   * Parses the value of a {@link BigDecimal} into a {@link Rational}
   * @param f {@link BigDecimal} value
   * @return {@link Rational} value
   */
  public static Rational valueOf(BigDecimal f) {
    return new Rational(f, BigDecimal.ONE);
  }
  
  /**
   * Parses the value of a {@link BigInteger} into a {@link Rational}
   * @param i {@link BigInteger} value
   * @return {@link Rational} value
   */
  public static Rational valueOf(BigInteger i) {
    return new Rational(i, BigInteger.ONE);
  }
  
  /**
   * Parses the value of a <code>int</code> into a {@link Rational}
   * @param i int value
   * @return {@link Rational} value
   */
  public static Rational valueOf(int i) {
    return Rational.valueOf(BigInteger.valueOf(i));
  }
  
  /**
   * Parses the value of a <code>long</code> into a {@link Rational}
   * @param i long value
   * @return {@link Rational} value
   */
  public static Rational valueOf(long i) {
    return Rational.valueOf(BigInteger.valueOf(i));
  }
  
  /**
   * Parses the value of a <code>double</code> into a {@link Rational}
   * @param f double value
   * @return {@link Rational} value
   */
  public static Rational valueOf(double f) {
    return Rational.valueOf(BigDecimal.valueOf(f));
  }
  
  /**
   * Parses the value of a <code>float</code> into a {@link Rational}
   * @param f float value
   * @return {@link Rational} value
   */
  public static Rational valueOf(float f) {
    return Rational.valueOf(BigDecimal.valueOf(f));
  }
  
  /**
   * Parses a {@link String} value into a {@link Rational}
   * @param s {@link String} value
   * @return {@link Rational} value
   */
  public static Rational valueOf(String s) {
    final int fractionHasAtMostParts = 2;
    
    if (s == null) {
      throw new NullPointerException("Value may not be null.");
    }
    if (FLOATING_POINT_PATTERN.matcher(s).matches()) {
      return Rational.valueOf(new BigDecimal(s));
    }
    if (RATIONAL_PATTERN.matcher(s).matches()) {
      final String[] rationalParts = s.split("[/]{1}");
      
      if (rationalParts.length > 2) {
        throw new NumberFormatException("Rational value was not well formatted as [-]x/[-]y.");
      }
      else if (rationalParts.length == fractionHasAtMostParts) {
        return new Rational(new BigDecimal(rationalParts[0]), new BigDecimal(rationalParts[1]));
      }
      else {
        return new Rational(new BigDecimal(rationalParts[0]), BigDecimal.ONE);
      }
    }
    
    throw new NumberFormatException("Rational value was not well formatted as [-]x/[-]y.");
  }
  
  @Override
  public String toString() {
    return String.format("%1$s/%2$s", numerator, denominator);
  }
  
  @Override
  public int hashCode() {
    int result = 53;
    
    result = (int) (37 * result + Double.doubleToLongBits(this.doubleValue()));
    
    return result;
  }
  
  /**
   * Determines whether this and another object are equal to each other. This can compare to any floating point and
   * integer type, including {@link Rational}, {@link BigInteger}, {@link BigDecimal}, {@link Double}, {@link Float},
   * {@link Long}, {@link Integer}, {@link Short}, {@link Byte}.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Rational) {
      return Rational.compareValues(this, (Rational) other);
    }
    
    if (other instanceof BigInteger) {
      return bigIntegerValue().equals((BigInteger) other);
    }
    
    if (other instanceof BigDecimal) {
      return bigDecimalValue().equals((BigDecimal) other);
    }
    
    if (other instanceof Double) {
      return Double.valueOf(doubleValue()).equals((Double) other);
    }
    
    if (other instanceof Float) {
      return Float.valueOf(floatValue()).equals((Float) other);
    }
    
    if (other instanceof Long) {
      return Long.valueOf(longValue()).equals((Long) other);
    }
    
    if (other instanceof Integer) {
      return Integer.valueOf(intValue()).equals((Integer) other);
    }
    
    if (other instanceof Short) {
      return Short.valueOf(shortValue()).equals((Short) other);
    }
    
    if (other instanceof Byte) {
      return Byte.valueOf(byteValue()).equals((Byte) other);
    }
    
    return false;
  }

}
