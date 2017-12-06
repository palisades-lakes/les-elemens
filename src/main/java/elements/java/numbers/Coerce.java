package elements.java.numbers;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.math3.fraction.BigFraction;

import clojure.lang.Ratio;

//----------------------------------------------------------------
/** Coercion between <code>Number</code> classes.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-04-11
 * @version 2017-05-03
 */

public final class Coerce {

  //--------------------------------------------------------------

  public static final BigFraction toBigFraction (final Number x) {

    if (x instanceof BigFraction) { return (BigFraction) x; }

    if (x instanceof Ratio) {
      return new BigFraction(((Ratio) x).numerator,
        ((Ratio) x).denominator); }

    if (x instanceof BigInteger)  {
      return new BigFraction((BigInteger) x); }

    if (x instanceof BigDecimal) {
      final BigDecimal xx = (BigDecimal) x;
      final int scale = xx.scale();
      if (scale >= 0) {
        return new BigFraction(xx.unscaledValue(),
          BigInteger.TEN.pow(scale)); }
      return new BigFraction(xx.unscaledValue()
        .multiply(BigInteger.TEN.pow(-scale))); }

    if (x instanceof Double) {
      return new BigFraction(((Double) x).doubleValue()); }

    if (x instanceof Float) {
      return new BigFraction(((Float) x).doubleValue()); }

    if (x instanceof Long) {
      return new BigFraction(((Long) x).longValue()); }

    if (x instanceof Integer) {
      return new BigFraction(((Integer) x).intValue()); }

    if (x instanceof Short) {
      return new BigFraction(((Short) x).intValue()); }

    if (x instanceof Byte) {
      return new BigFraction(((Byte) x).intValue()); }

    throw new
    UnsupportedOperationException(x.getClass().getSimpleName()); }

  //--------------------------------------------------------------

  public static final BigDecimal toBigDecimal (final Number x) {

    if (x instanceof BigDecimal) { return (BigDecimal) x; }

    if (x instanceof BigInteger)  {
      return new BigDecimal((BigInteger) x); }

    if (x instanceof BigFraction) {
      return ((BigFraction) x).bigDecimalValue(); }

    if (x instanceof Ratio) {
      return ((Ratio) x).decimalValue(); }

    if (x instanceof Double) {
      return new BigDecimal(((Double) x).doubleValue()); }

    if (x instanceof Float) {
      return new BigDecimal(((Float) x).doubleValue()); }

    if (x instanceof Long) {
      return new BigDecimal(((Long) x).longValue()); }

    if (x instanceof Integer) {
      return new BigDecimal(((Integer) x).intValue()); }

    if (x instanceof Short) {
      return new BigDecimal(((Short) x).intValue()); }

    if (x instanceof Byte) {
      return new BigDecimal(((Byte) x).intValue()); }

    throw new
    UnsupportedOperationException(x.getClass().getSimpleName()); }

  //--------------------------------------------------------------

  public static final BigInteger toBigInteger (final Number x) {
    if (x instanceof BigInteger) { return (BigInteger) x; }
    return BigInteger.valueOf(x.longValue()); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private Coerce () {
    throw new UnsupportedOperationException("can't instantiate" +
      getClass().getSimpleName()); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
