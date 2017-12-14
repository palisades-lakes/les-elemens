package palisades.lakes.elements.java.numbers.sum;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.math3.fraction.BigFraction;

import palisades.lakes.elements.java.numbers.Coerce;

//----------------------------------------------------------------
/** Least promotion addition of instances of <code>Number</code>.
 * Experimental, sould be auto-generated somehow.
 *
 * TODO: clojure java templates?
 *
 * @author palisades dot lakes at gmail dot com
 * @since 2017-04-11
 * @version 2017-05-03
 */

@SuppressWarnings("boxing")
public final class Add {

  //--------------------------------------------------------------

  private static final BigFraction add (final BigFraction x,
                                        final Number y) {
    return x.add(Coerce.toBigFraction(y)); }

  //--------------------------------------------------------------

  private static final BigDecimal add (final BigDecimal x,
                                       final Number y) {
    return x.add(Coerce.toBigDecimal(y)); }

  //--------------------------------------------------------------

  private static final BigDecimal add (final BigInteger x,
                                       final Double y) {
    return Coerce.toBigDecimal(x).add(Coerce.toBigDecimal(y)); }

  private static final BigDecimal add (final BigInteger x,
                                       final Float y) {
    return Coerce.toBigDecimal(x).add(Coerce.toBigDecimal(y)); }

  private static final BigInteger add (final BigInteger x,
                                       final Number y) {
    return x.add(Coerce.toBigInteger(y)); }

  //--------------------------------------------------------------

  public static final Number add (final Number x,
                                  final Number y) {

    if (x instanceof BigFraction) {
      return add((BigFraction) x, y); }
    if (y instanceof BigFraction) {
      return add((BigFraction) y, x); }

    if (x instanceof BigDecimal) {
      return add((BigDecimal) x, y); }
    if (y instanceof BigDecimal) {
      return add((BigDecimal) y, x); }

    if (x instanceof BigInteger) {
      if (y instanceof Double) {
        return add((BigInteger) x, (Double) y); }
      else if (y instanceof Float) {
        return add((BigInteger) x, (Float) y); }
      else {
        return add((BigInteger) x, y); } }

    if (y instanceof BigInteger) {
      if (x instanceof Double) {
        return add((BigInteger) y, (Double) x); }
      else if (y instanceof Float) {
        return add((BigInteger) y, (Float) x); }
      else {
        return add((BigInteger) y, x); }  }

    if (x instanceof Double) {
      return x.doubleValue() + y.doubleValue(); }
    if (y instanceof Double) {
      return x.doubleValue() + y.doubleValue(); }

    if (x instanceof Float) {
      return x.floatValue() + y.floatValue(); }
    if (y instanceof Float) {
      return x.floatValue() + y.floatValue(); }

    if (x instanceof Long) {
      return x.longValue() + y.longValue(); }
    if (y instanceof Long) {
      return x.longValue() + y.longValue(); }

    return x.intValue() + y.intValue(); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private Add () {
    throw new UnsupportedOperationException("can't instantiate" +
      getClass().getSimpleName()); }

  //--------------------------------------------------------------
} // end of class
//----------------------------------------------------------------
