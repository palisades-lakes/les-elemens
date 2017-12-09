package palisades.lakes.elements.java.numbers.sum;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.fraction.BigFraction;

import clojure.lang.ISeq;
import clojure.lang.Numbers;
import clojure.lang.Ratio;
import palisades.lakes.elements.java.numbers.sum.Add;

//--------------------------------------------------------------
/** Default (left-to-right) accumulation of sums.
 * Not a good choice for floating point numbers.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-04-11
 * @version 2017-05-03
 */

public final class Naive {

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final long sum (final byte[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    long s = x[start];
    for (int i = start + 1; i < end; i++) { s += x[i]; }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>. */

  public static final long sum (final byte[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final long sum (final short[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    long s = x[start];
    for (int i = start + 1; i < end; i++) { s += x[i]; }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>. */

  public static final long sum (final short[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final long sum (final int[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    long s = x[start];
    for (int i = start + 1; i < end; i++) { s += x[i]; }
    return s; }

  //  public static final double sum (final int[] x,
  //                                    final int start,
  //                                    final int end) {
  //    return sumLong(x,start,end); }

  /** Sum the palisades.lakes.elements of <code>x</code>. */

  public static final long sum (final int[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final long sum (final long[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    long s = x[start];
    for (int i = start + 1; i < end; i++) { s += x[i]; }
    return s; }

  //  public static final double sum (final long[] x,
  //                                    final int start,
  //                                    final int end) {
  //    return sumLong(x,start,end); }

  /** Sum the palisades.lakes.elements of <code>x</code>. */

  public static final long sum (final long[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final long sum (final Byte[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    long s = x[start].longValue();
    for (int i = start + 1; i < end; i++) { s += x[i].intValue(); }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>. */

  public static final long sum (final Byte[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final long sum (final Short[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    long s = x[start].longValue();
    for (int i = start + 1; i < end; i++) { s += x[i].intValue(); }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>. */

  public static final long sum (final Short[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final long sum (final Integer[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    long s = x[start].longValue();
    for (int i = start + 1; i < end; i++) { s += x[i].intValue(); }
    return s; }

  //  public static final double sum (final int[] x,
  //                                    final int start,
  //                                    final int end) {
  //    return sumLong(x,start,end); }

  /** Sum the palisades.lakes.elements of <code>x</code>. */

  public static final long sum (final Integer[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final long sum (final Long[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    long s = x[start].longValue();
    for (int i = start + 1; i < end; i++) { s += x[i].longValue(); }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>. */

  public static final long sum (final Long[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /**
   * Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final double sum (final float[] x,
                                  final int start,
                                  final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    double s = x[start];
    for (int i = start + 1; i < end; i++) { s += x[i]; }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>. */

  public static final double sum (final float[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /**
   * Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final double sum (final double[] x,
                                  final int start,
                                  final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    double s = x[start];
    for (int i = start + 1; i < end; i++) { s += x[i]; }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>. */

  public static final double sum (final double[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   * Accumulate as a Number.
   */

  public static final double sum (final Float[] x,
                                  final int start,
                                  final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    double s = x[start].doubleValue();
    for (int i = start + 1; i < end; i++) {
      s = s + x[i].doubleValue(); }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>. */

  public static final double sum (final Float[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   * Accumulate as a Number.
   */

  public static final double sum (final Double[] x,
                                  final int start,
                                  final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    double s = x[start].doubleValue();
    for (int i = start + 1; i < end; i++) {
      s = s + x[i].doubleValue(); }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>. */

  public static final double sum (final Double[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /**
   * Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   * Accumulate as a BigDecimal.
   */

  public static final BigDecimal sum (final BigDecimal[] x,
                                      final int start,
                                      final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    BigDecimal s = x[start];
    for (int i = start + 1; i < end; i++) { s = s.add(x[i]); }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>. */

  public static final BigDecimal sum (final BigDecimal[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   * Accumulate as a Number.
   */

  public static final BigFraction sum (final BigFraction[] x,
                                       final int start,
                                       final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    BigFraction s = x[start];
    for (int i = start + 1; i < end; i++) { s = s.add(x[i]); }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>.
   */

  public static final BigFraction sum (final BigFraction[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   * Accumulate as a Number.
   */

  public static final Ratio sum (final Ratio[] x,
                                 final int start,
                                 final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    Ratio s = x[start];
    for (int i = start + 1; i < end; i++) {
      s = Numbers.toRatio(Numbers.add(s,x[i])); }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>.
   */

  public static final Ratio sum (final Ratio[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   * Accumulate as a Number.
   */

  public static final Number sum (final Number[] x,
                                  final int start,
                                  final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    Number s = x[start];
    for (int i = start + 1; i < end; i++) { s = Add.add(s,x[i]); }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>.
   */

  public static final Number sum (final Number[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   * Accumulate as a Number.
   */

  public static final Number sum (final Object[] x,
                                  final int start,
                                  final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    Number s = (Number) x[start];
    for (int i = start + 1; i < end; i++) {
      s = Add.add(s,(Number) x[i]); }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>.
   */

  public static final Number sum (final Object[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code>.
   */

  public static final Number sum (final Iterable x) {
    final Iterator it = x.iterator();
    Number s = Double.valueOf(0.0);
    while (it.hasNext()) { s = Add.add(s,(Number) it.next()); }
    return s; }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code>.
   */

  public static final Number sum (final ISeq x) {
    ISeq it = x;
    Number s = Double.valueOf(0.0);
    while (null != it) {
      s = Add.add(s,(Number) it.first());
      it = it.next();}
    return s; }

  //--------------------------------------------------------------
  /** Sum the palisades.lakes.elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   * Accumulate as a Number.
   */

  public static final Number sum (final List x,
                                  final int start,
                                  final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.size();

    Number s = (Number) x.get(start);
    for (int i = start + 1; i < end; i++) {
      s = Add.add(s,(Number) x.get(i)); }
    return s; }

  /** Sum the palisades.lakes.elements of <code>x</code>.
   */

  public static final Number sum (final List x) {
    return sum(x,0,x.size()); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private Naive () {
    throw new UnsupportedOperationException("can't instantiate" +
      getClass().getSimpleName()); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
