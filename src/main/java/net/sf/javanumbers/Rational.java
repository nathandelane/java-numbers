package net.sf.javanumbers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Copyright (C) 2013 Nathan Lane <nathan.david.lane@gmail.com>
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
 * @author nathandelane <nathan.david.lane@gmail.com>
 *
 */
public class Rational extends Number implements Comparable<Rational> {

  public static final Rational ONE = Rational.valueOf(1);
  public static final Rational ZERO = Rational.valueOf(0);

  private static final long serialVersionUID = -1093296620458469836L;
  
  private static final BigDecimal NEGATIVE_ONE = new BigDecimal("-1.0");
  private static final Pattern RATIONAL_PATTERN = Pattern.compile("^([\\-]{0,1}[\\d]+)/([\\-]{0,1}[\\d]+)$");
  private static final Pattern FLOATING_POINT_PATTERN = Pattern.compile("([\\-]{0,1}[\\d]*)\\.([\\d]+)$");
  private static final int DEFAULT_BIG_DECIMAL_SCALE = 32;
  private static final RoundingMode DEFAULT_BIG_DECIMAL_ROUNDING_MODE = RoundingMode.HALF_UP;

  private final BigDecimal numerator;
  private final BigDecimal denominator;
  private final boolean isRational;
  private final boolean isComplex;

  private int scale;
  private RoundingMode roundingMode;

  /**
   * Creates a Rational from two {@link BigDecimal} values.
   * @param numerator
   * @param denominator
   */
  public Rational(BigDecimal numerator, BigDecimal denominator) {
    if (numerator == null || denominator == null) {
      throw new IllegalArgumentException("Numerator and denominator may not be null.");
    }
    else if (denominator.intValue() == 0) {
      throw new ArithmeticException("Denominator may not be zero.");
    }

    final BigDecimal n = numerator;
    final BigDecimal d = denominator;

    if (isNegative(n, d)) {
      this.numerator = n.abs().multiply(NEGATIVE_ONE);
      this.denominator = d.abs();
    }
    else {
      this.numerator = n.abs();
      this.denominator = d.abs();
    }

    this.isRational = true;
    this.isComplex = false;
    this.scale = Rational.DEFAULT_BIG_DECIMAL_SCALE;
    this.roundingMode = DEFAULT_BIG_DECIMAL_ROUNDING_MODE;
  }

  /**
   * Creates a Rational from two {@link BigInteger} values.
   * @param numerator
   * @param denominator
   */
  public Rational(BigInteger numerator, BigInteger denominator) {
    if (numerator == null || denominator == null) {
      throw new IllegalArgumentException("Numerator and denominator may not be null.");
    }
    else if (denominator.intValue() == 0) {
      throw new ArithmeticException("Denominator may not be zero.");
    }

    final BigDecimal n = new BigDecimal(numerator);
    final BigDecimal d = new BigDecimal(denominator);

    if (isNegative(n, d)) {
      this.numerator = n.abs().multiply(NEGATIVE_ONE);
      this.denominator = d.abs();
    }
    else {
      this.numerator = n.abs();
      this.denominator = d.abs();
    }

    this.isRational = true;
    this.isComplex = false;
    this.scale = Rational.DEFAULT_BIG_DECIMAL_SCALE;
    this.roundingMode = DEFAULT_BIG_DECIMAL_ROUNDING_MODE;
  }

  /**
   * Creates a Rational from two {@link BigDecimal} values. This constructor is used internally by Rational.
   * This is marked not complex.
   * @param numerator
   * @param denominator
   * @param isRational
   */
  private Rational(BigDecimal numerator, BigDecimal denominator, boolean isRational) {
    if (numerator == null || denominator == null) {
      throw new IllegalArgumentException("Numerator and denominator may not be null.");
    }
    else if (denominator.intValue() == 0) {
      throw new ArithmeticException("Denominator may not be zero.");
    }

    final BigDecimal n = numerator;
    final BigDecimal d = denominator;

    if (isNegative(n, d)) {
      this.numerator = n.abs().multiply(NEGATIVE_ONE);
      this.denominator = d.abs();
    }
    else {
      this.numerator = n.abs();
      this.denominator = d.abs();
    }

    this.isRational = isRational;
    this.isComplex = false;
    this.scale = Rational.DEFAULT_BIG_DECIMAL_SCALE;
    this.roundingMode = DEFAULT_BIG_DECIMAL_ROUNDING_MODE;
  }

  /**
   * Creates a Rational from two int values. This is marked rational and not complex.
   * @param numerator
   * @param denominator
   */
  public Rational(int numerator, int denominator) {
    if (denominator == 0) {
      throw new ArithmeticException("Denominator may not be zero.");
    }

    final BigDecimal n = BigDecimal.valueOf(Long.valueOf(numerator));
    final BigDecimal d = BigDecimal.valueOf(Long.valueOf(denominator));

    if (isNegative(n, d)) {
      this.numerator = n.abs().multiply(NEGATIVE_ONE);
      this.denominator = d.abs();
    }
    else {
      this.numerator = n.abs();
      this.denominator = d.abs();
    }

    this.isRational = true;
    this.isComplex = false;
    this.scale = Rational.DEFAULT_BIG_DECIMAL_SCALE;
    this.roundingMode = DEFAULT_BIG_DECIMAL_ROUNDING_MODE;
  }

  /**
   * Creates a Rational from two long values. This is marked rational and not complex.
   * @param numerator
   * @param denominator
   */
  public Rational(long numerator, long denominator) {
    if (denominator == 0) {
      throw new ArithmeticException("Denominator may not be zero.");
    }

    final BigDecimal n = BigDecimal.valueOf(numerator);
    final BigDecimal d = BigDecimal.valueOf(denominator);

    if (isNegative(n, d)) {
      this.numerator = n.abs().multiply(NEGATIVE_ONE);
      this.denominator = d.abs();
    }
    else {
      this.numerator = n.abs();
      this.denominator = d.abs();
    }

    this.isRational = true;
    this.isComplex = false;
    this.scale = Rational.DEFAULT_BIG_DECIMAL_SCALE;
    this.roundingMode = DEFAULT_BIG_DECIMAL_ROUNDING_MODE;
  }

  /**
   * Copy constructor.
   * @param rational
   */
  public Rational(Rational rational) {
    if (rational == null) {
      throw new IllegalArgumentException("Argument may not be null.");
    }

    this.numerator = rational.numerator;
    this.denominator = rational.denominator;
    this.isRational = rational.isRational;
    this.isComplex = rational.isComplex;
    this.scale = rational.scale;
    this.roundingMode = rational.roundingMode;
  }
  
  /**
   * Constructor of Rationals. Calculates the resulting Rational using BigInteger arithmetic.
   * Rationalilty and complexity are the result of the conjunction of each Rational's value.
   * Scale is the maximum scale. Rounding mode is taken from the numerator.
   * @param numerator
   * @param denominator
   */
  public Rational(Rational numerator, Rational denominator) {
    if (numerator == null) {
      throw new IllegalArgumentException("Numerator may not be null.");
    }
    if (denominator == null) {
      throw new IllegalArgumentException("Denominator may not be null.");
    }

    this.numerator = numerator.bigDecimalValue();
    this.denominator = denominator.bigDecimalValue();
    this.isRational = numerator.isRational && denominator.isRational;
    this.isComplex = numerator.isComplex && denominator.isComplex;
    this.scale = Math.max(numerator.scale, denominator.scale);
    this.roundingMode = numerator.roundingMode;
  }

  public Rational(BigDecimal numerator, BigDecimal denominator, boolean isRational, boolean isComplex) {
    if (numerator == null || denominator == null) {
      throw new IllegalArgumentException("Numerator and denominator may not be null.");
    }
    else if (denominator.intValue() == 0) {
      throw new ArithmeticException("Denominator may not be zero.");
    }

    final BigDecimal n = numerator;
    final BigDecimal d = denominator;

    if (isNegative(n, d)) {
      this.numerator = n.abs().multiply(NEGATIVE_ONE);
      this.denominator = d.abs();
    }
    else {
      this.numerator = n.abs();
      this.denominator = d.abs();
    }

    this.isRational = isRational;
    this.isComplex = isComplex;
    this.scale = Rational.DEFAULT_BIG_DECIMAL_SCALE;
    this.roundingMode = DEFAULT_BIG_DECIMAL_ROUNDING_MODE;
  }

  /**
   * Gets the numerator of this Rational.
   * @return
   */
  public BigDecimal getNumerator() {
    return numerator;
  }

  /**
   * Gets the denominator of this rational.
   * @return
   */
  public BigDecimal getDenominator() {
    return denominator;
  }

  /**
   * Returns BigInteger value, trucated from division.
   * @return
   */
  public BigInteger bigIntegerValue() {
    return (numerator.divide(denominator)).toBigInteger();
  }

  /**
   * Gets the current BigDecimal scale of this Rational, which is by default 32.
   * @return
   */
  public int getScale() {
    return scale;
  }

  /**
   * Sets the current BigDecimal scale for this Rational.
   * @param scale
   * @return
   */
  public Rational setScale(int scale) {
    this.scale = scale;

    return this;
  }

  /**
   * Gets the current BigDecimal rounding mode.
   * @return
   */
  public RoundingMode getRoundingMode() {
    return roundingMode;
  }

  /**
   * Sets the current BigDecimal rounding mode.
   * @param roundingMode
   * @return
   */
  public Rational setRoundingMode(RoundingMode roundingMode) {
    this.roundingMode = roundingMode;

    return this;
  }

  /**
   * Retuns BigDecimal value.
   * @return
   */
  public BigDecimal bigDecimalValue() {
    return (numerator.divide(denominator, scale, roundingMode));
  }

  /**
   * Returns BigDecimal value with the given scale and rounding mode.
   * @param newScale
   * @param newRoundingMode
   * @return
   */
  public BigDecimal bigDecimalValue(int newScale, RoundingMode newRoundingMode) {
    if (newRoundingMode == null) {
      throw new IllegalArgumentException("RoundingMode may not be null.");
    }

    return (numerator.divide(denominator, newScale, newRoundingMode));
  }
  
  /**
   * Returns the byte value, truncated from division operation.
   */
  public byte byteValue() {
    return (numerator.divide(denominator)).byteValue();
  }
  
  /**
   * Returns the short value, truncated from division operation.
   */
  public short shortValue() {
    return (numerator.divide(denominator)).shortValue();
  }

  /**
   * Returns integer value, truncated from division operation.
   */
  @Override
  public int intValue() {
    return (numerator.divide(denominator)).intValue();
  }

  /**
   * Returns long value, truncated from division operation.
   */
  @Override
  public long longValue() {
    return (numerator.divide(denominator)).longValue();
  }

  /**
   * Returns float value. Possible precision problem.
   */
  @Override
  public float floatValue() {
    return (numerator.divide(denominator, scale, roundingMode)).floatValue();
  }

  /**
   * Returns double value. Possible precision problem.
   */
  @Override
  public double doubleValue() {
    return (numerator.divide(denominator, scale, roundingMode)).doubleValue();
  }

  /**
   * Returns the absolute value of this Rational.
   * @return
   */
  public Rational abs() {
    return new Rational(numerator.abs(), denominator.abs());
  }

  /**
   * Returns the reciprocal of this Rational.
   * @return
   */
  public Rational reciprocal() {
    return new Rational(denominator, numerator);
  }

  /**
   * Compares a Rational to another number.
   */
  public int compareTo(Rational other) {
    return ((other.reduce()).numerator.compareTo((this.reduce()).numerator)) + ((other.reduce()).denominator.compareTo((this.reduce()).denominator));
  }

  /**
   * Determines the greatest common denominator of this and another Rational's denominator.
   * This diverges from the internal representation of Rational and utilizes the denominator, solely.
   * @param other
   * @return
   */
  public Rational gcd(Rational other) {
    final BigInteger gcd = gcd(denominator, other.denominator);

    if (gcd.compareTo(BigInteger.ONE) == 0) {
      return new Rational(this.denominator.multiply(other.denominator), BigDecimal.ONE);
    }

    return new Rational(gcd, BigInteger.ONE);
  }
  
  /**
   * Determines the greatest common denominator of two BigDecimal values.
   * @param left
   * @param right
   * @return
   */
  private BigInteger gcd(BigDecimal left, BigDecimal right) {
    final Rational leftNormalized = Rational.valueOf(left);
    final Rational rightNormalized = Rational.valueOf(right);
    
    return leftNormalized.bigIntegerValue().gcd(rightNormalized.bigIntegerValue());
  }

  /**
   * Reduces radical to the most reduced radical form.
   * @return
   */
  public Rational reduce() {
    final BigDecimal gcd = new BigDecimal(gcd(numerator, denominator));
    final BigDecimal reducedNumerator = numerator.divide(gcd);
    final BigDecimal reducedDenominator = denominator.divide(gcd);

    return new Rational(reducedNumerator, reducedDenominator);
  }

  /**
   * Determines which of this and another Rational is greater.
   * @param other
   * @return
   */
  public Rational max(Rational other) {
    final BigDecimal gcd = new BigDecimal(gcd(other).bigIntegerValue());
    final Rational newThis = new Rational(
      (this.denominator.equals(gcd) ? this.numerator : this.numerator.multiply(gcd.divide(this.denominator))),
      (this.denominator.equals(gcd) ? this.denominator : gcd)
    );
    final Rational newOther = new Rational(
      (other.denominator.equals(gcd) ? other.numerator : other.numerator.multiply(gcd.divide(other.denominator))),
      (other.denominator.equals(gcd) ? other.denominator : gcd)
    );

    if (newOther.numerator.compareTo(newThis.numerator) > 0) {
      return other;
    }

    return this;
  }

  /**
   * Determines which of this and another Rational is greater.
   * @param other
   * @return
   */
  public Rational min(Rational other) {
    final BigDecimal gcd = new BigDecimal(gcd(other).bigIntegerValue());
    final Rational newThis = new Rational(
      (this.denominator.equals(gcd) ? this.numerator : this.numerator.multiply(gcd.divide(this.denominator))),
      (this.denominator.equals(gcd) ? this.denominator : gcd)
    );
    final Rational newOther = new Rational(
      (other.denominator.equals(gcd) ? other.numerator : other.numerator.multiply(gcd.divide(other.denominator))),
      (other.denominator.equals(gcd) ? other.denominator : gcd)
    );

    if (newOther.numerator.compareTo(newThis.numerator) < 0) {
      return other;
    }

    return this;
  }

  /**
   * Returns a Rational whose value is -a/b.
   * @return
   */
  public Rational negate() {
    return new Rational(numerator.negate(), denominator);
  }

  /**
   * Adds this to another Rational.
   * @param other
   * @return
   */
  public Rational add(Rational other) {
    if (!(this.isComplex && other.isComplex)) {
      throw new ArithmeticException("Cannot add a complex number to a real number.");
    }
    
    final BigDecimal gcd = new BigDecimal(gcd(other).reduce().bigIntegerValue());

    Rational newThis = new Rational(
      (this.denominator.equals(gcd) ? this.numerator : this.numerator.multiply(gcd.divide(this.denominator))),
      (this.denominator.equals(gcd) ? this.denominator : gcd)
    );
    Rational newOther = new Rational(
      (other.denominator.equals(gcd) ? other.numerator : other.numerator.multiply(gcd.divide(other.denominator))),
      (other.denominator.equals(gcd) ? other.denominator : gcd)
    );

    return new Rational(newThis.numerator.add(newOther.numerator), gcd);
  }

  /**
   * Subtracts another Rational from this.
   * @param other
   * @return
   */
  public Rational subtract(Rational other) {
    if (!(this.isComplex && other.isComplex)) {
      throw new ArithmeticException("Cannot subtract a complex number from a real number.");
    }
    
    final BigDecimal gcd = new BigDecimal(gcd(other).bigIntegerValue());
    final Rational newThis = new Rational(
      (this.denominator.equals(gcd) ? this.numerator : this.numerator.multiply(gcd.divide(this.denominator))),
      (this.denominator.equals(gcd) ? this.denominator : gcd)
    );
    final Rational newOther = new Rational(
      (other.denominator.equals(gcd) ? other.numerator : other.numerator.multiply(gcd.divide(other.denominator))),
      (other.denominator.equals(gcd) ? other.denominator : gcd)
    );

    return new Rational(newThis.numerator.subtract(newOther.numerator), gcd);
  }

  /**
   * Multiplies another Rational with this.
   * @param other
   * @return
   */
  public Rational multiply(Rational other) {
    if (!(this.isComplex && other.isComplex)) {
      throw new ArithmeticException("Cannot multiply a complex number and a real number.");
    }

    return new Rational(this.numerator.multiply(other.numerator), this.denominator.multiply(other.denominator));
  }

  /**
   * Divides this Rational by another Rational.
   * @param other
   * @return
   */
  public Rational divide(Rational other) {
    if (other.reduce().equals(Rational.ZERO)) {
      throw new ArithmeticException("Divid by zero.");
    }
    if (!(this.isComplex && other.isComplex)) {
      throw new ArithmeticException("Cannot divide a complex number and a real number.");
    }
    
    return new Rational(this.numerator.multiply(other.denominator), this.denominator.multiply(other.numerator));
  }

  /**
   * Returns a Rational whose value is this<sup>exponent</sup>.
   * @param exponent
   * @return
   */
  public Rational pow(int exponent) {
    final boolean resultIsNegative = ((exponent < 0) && (exponent % 2 != 0));

    Rational root = new Rational(1, 1);

    for (int i = 0; i < Math.abs(exponent); i++) {
      root = root.multiply(this);
    }

    return (resultIsNegative ? root.negate() : root);
  }
  
  /**
   * Probable loss of precision because I'm using the factory {@link Math#sqrt(double)} method.
   * @return
   */
  public Rational sqrt() {
    boolean isComplex = doubleValue() < 0;
    boolean isRational = true;
    
    final double sqrtNumerator = Math.sqrt(numerator.abs().doubleValue());
    final double sqrtDenominator = Math.sqrt(denominator.abs().doubleValue());
    final Rational doubleSqrt = Rational.valueOf(sqrtNumerator).divide(Rational.valueOf(sqrtDenominator));
    final BigDecimal newNumerator = doubleSqrt.numerator;
    final BigDecimal newDenominator = doubleSqrt.denominator;
    
    return new Rational(newNumerator, newDenominator, isRational, isComplex);
  }
  
  /**
   * Gets whether this Rational is an irrational number.
   * @return
   */
  public boolean isIrrational() {
    return !isRational;
  }
  
  /**
   * Gets whether this Rational is a complex number, meaning it has an imaginary portion.
   * @return
   */
  public boolean isComplex() {
    return isComplex;
  }

  /**
   * Determines whether this and another object are equal to each other. This can compare to any 
   * floating point and integer type, including {@link Rational}, {@link BigInteger}, {@link BigDecimal},
   * {@link Double}, {@link Float}, {@link Long}, {@link {Integer}, {@link Short}, {@link Byte}. 
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
  
  /**
   * Compares two values for equality.
   * @param left
   * @param right
   * @return
   */
  public static boolean compareValues(Rational left, Rational right) {
    final Rational reducedLeft = left.reduce();
    final Rational reducedRight = right.reduce();
    
    return (
      reducedLeft.numerator.equals(reducedRight.numerator) &&
      reducedLeft.denominator.equals(reducedRight.denominator)
    );
  }

  /**
   * Returns hash code for this Rational object.
   */
  @Override
  public int hashCode() {
    int result = 53;
    
    result = (int) (37 * result + Double.doubleToLongBits(this.doubleValue()));
    
    return  result;
  }

  /**
   * Returns string value of this Rational object. If this Rational is a whole number, then this method
   * will return the whole number as a String.
   */
  @Override
  public String toString() {
    return denominator.equals(BigInteger.ONE) ? numerator.abs().toString() : String.format("%1$s/%2$s", numerator.abs(), denominator.abs());
  }
  
  /**
   * Returns string value of this Rational object reduced. If this Rational is a whole number, then this method
   * will return the whole number as a String.
   * @param reduce Whether or not to reduce this value first.
   */
  public String toString(boolean reduce) {
    final Rational reduced = reduce ? this.reduce() : this;
    
    return reduced.denominator.equals(BigInteger.ONE) ? reduced.numerator.abs().toString() : String.format("%1$s/%2$s", reduced.numerator.abs(), reduced.denominator.abs());
  }
  
  /**
   * Always returns a rational value as a String.
   * @return this Rational's value as a rational value.
   */
  public String toRationalString() {
    return String.format("%1$s/%2$s", numerator.abs(), denominator.abs());
  }
  
  /**
   * Always returns a rational value as a String.
   * @param reduce Whether or not to reduce this value first.
   * @return this Rational's value reduced as a rational value.
   */
  public String toRationalString(boolean reduce) {
    final Rational reduced = reduce ? this.reduce() : this;
    
    return String.format("%1$s/%2$s", reduced.numerator.abs(), reduced.denominator.abs());
  }
  
  /**
   * Determines whether this Rational is negative, based on the numerator and the denominator.
   * @param numerator
   * @param denominator
   * @return
   */
  private boolean isNegative(BigDecimal numerator, BigDecimal denominator) {
    boolean isNegative = false;

    if (numerator.compareTo(BigDecimal.ZERO) < 0 && denominator.compareTo(BigDecimal.ZERO) > 0) {
      isNegative = true;
    }
    else if (numerator.compareTo(BigDecimal.ZERO) > 0 && denominator.compareTo(BigDecimal.ZERO) < 0) {
      isNegative = true;
    }

    return isNegative;
  }

  /**
   * Factory method for creating a Rational from a String.
   * @param s
   * @return
   * @throws NumberFormatException
   */
  public static Rational valueOf(String s) throws NumberFormatException {
    final Matcher rationalPartsMatcher = RATIONAL_PATTERN.matcher(s);
    final Matcher floatingPointPartsMatcher = FLOATING_POINT_PATTERN.matcher(s);

    Rational result = null;

    if (rationalPartsMatcher.matches() && rationalPartsMatcher.groupCount() == 2) {
      final BigInteger numerator = new BigInteger(rationalPartsMatcher.group(1));
      final BigInteger denominator = new BigInteger(rationalPartsMatcher.group(2));

      result = new Rational(numerator, denominator);
    }
    else if (floatingPointPartsMatcher.matches() && floatingPointPartsMatcher.groupCount() == 2) {
      final BigDecimal dec = new BigDecimal(s);

      result = Rational.valueOf(dec);
    }
    else {
      throw new NumberFormatException("Expected [-]integer/[-]integer or floating point number.");
    }


    return result;
  }

  /**
   * Factory method for creating a Rational from an integer.
   * @param i
   * @return
   */
  public static Rational valueOf(int i) {
    return new Rational(BigInteger.valueOf((long) i), BigInteger.ONE);
  }

  /**
   * Factory method for creating a Rational from a long.
   * @param i
   * @return
   */
  public static Rational valueOf(long i) {
    return new Rational(BigInteger.valueOf(i), BigInteger.ONE);
  }

  /**
   * Factory method for creating a Rational from a BigInteger.
   * @param i
   * @return
   */
  public static Rational valueOf(BigInteger i) {
    return new Rational(i, BigInteger.ONE);
  }

  /**
   * Factory method for creating a Rational from a BigDecimal value.
   * @param f
   * @return
   */
  public static Rational valueOf(BigDecimal f) {
    final String[] rationalParts = String.format("%1$s", f).split("[\\.]{1}");

    if (rationalParts.length == 2) {
      return getRationalForTwoPartFloatingPointNumber(rationalParts);
    }
    else {
      return getRationalForSinglePartFloatingPointNumber(rationalParts);
    }
  }

  /**
   * Factory method for creating a Rational from a float value.
   * @param f
   * @return
   */
  public static Rational valueOf(float f) {
    final String[] rationalParts = String.format("%1$s", f).split("[\\.]{1}");

    if (rationalParts.length == 2) {
      return getRationalForTwoPartFloatingPointNumber(rationalParts);
    }
    else {
      return getRationalForSinglePartFloatingPointNumber(rationalParts);
    }
  }

  /**
   * Factory method for creating a Rational from a double value.
   * @param f
   * @return
   */
  public static Rational valueOf(double f) {
    final String[] rationalParts = String.format("%1$s", f).split("[\\.]{1}");

    if (rationalParts.length == 2) {
      return getRationalForTwoPartFloatingPointNumber(rationalParts);
    }
    else {
      return getRationalForSinglePartFloatingPointNumber(rationalParts);
    }
  }

  /**
   * Calculates a rational number for a multi-part floating point number.
   * @param rationalParts
   * @return
   */
  private static Rational getRationalForTwoPartFloatingPointNumber(String[] rationalParts) {
    final BigInteger wholePart = new BigInteger(rationalParts[0]);
    final BigInteger fractionalPart = new BigInteger(rationalParts[1]);
    final int placesInFractionalPart = rationalParts[1].length();
    final String formatString = String.format("1%%%1$sd", placesInFractionalPart);
    final BigInteger denominator = new BigInteger(String.format(formatString, 0).replace(' ', '0'));
    final BigInteger numerator = (wholePart.multiply(denominator)).add(fractionalPart);

    Rational reducedRational = (new Rational(numerator, denominator)).reduce();

    if (reducedRational.numerator.toString().contains(fractionalPart.toString())) {
      reducedRational = new Rational(reducedRational.numerator, reducedRational.denominator, false);
    }

    return reducedRational;
  }

  /**
   * Calculates a rational number for a single part floating point number.
   * @param rationalParts
   * @return
   */
  private static Rational getRationalForSinglePartFloatingPointNumber(String[] rationalParts) {
    final String fractionalPart = rationalParts[0];
    final BigInteger numerator = new BigInteger(fractionalPart);
    final int placesInFractionalPart = rationalParts[1].length();
    final String formatString = String.format("1%%%1$sd", placesInFractionalPart);
    final BigInteger denominator = new BigInteger(String.format(formatString, 0));

    Rational reducedRational = (new Rational(numerator, denominator)).reduce();

    if (reducedRational.numerator.toString().contains(fractionalPart)) {
      reducedRational = new Rational(reducedRational.numerator, reducedRational.denominator, false);
    }

    return reducedRational;
  }

}
