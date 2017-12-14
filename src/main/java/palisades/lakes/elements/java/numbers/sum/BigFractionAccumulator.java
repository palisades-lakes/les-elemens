package palisades.lakes.elements.java.numbers.sum;

import org.apache.commons.math3.fraction.BigFraction;

//--------------------------------------------------------------
/** Exact (left-to-right) accumulation of doubles using a
 *  BigFraction accumulator.
 *
 *  Note: BigFraction.doubleValue() may not round correctly, to
 *  nearest, so this may not be exact!
 *
 * @author palisades dot lakes at gmail dot com
 * @since 2017-05-12
 * @version 2017-05-18
 */

public final class BigFractionAccumulator {

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

    BigFraction s = new BigFraction(x[start]);
    for (int i = start + 1; i < end; i++) {
      s = s.add(new BigFraction(x[i])); }
    return s.doubleValue(); }

  /** Sum the palisades.lakes.elements of <code>x</code>. */

  public static final double sum (final double[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the absolute values of the palisades.lakes.elements of <code>x</code>
   * from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final double l1norm (final double[] x,
                                     final int start,
                                     final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    // use constructor, rather than BigFraction.valueOf(x), because
    // valueOf doesn't return the exact value, but rather the
    // BigFraction corresponding to the printed decimal version of
    // x

    BigFraction s = new BigFraction(x[start]);
    for (int i = start + 1; i < end; i++) {
      s = s.add(new BigFraction(Math.abs(x[i]))); }
    return s.doubleValue(); }

  /** Sum the absolute values of the palisades.lakes.elements of <code>x</code>.
   */

  public static final double l1norm (final double[] x) {
    return l1norm(x,0,x.length); }

  //--------------------------------------------------------------
  /** l1norm(x) / sum(x). */

  public static final double sumCondition (final double[] x,
                                           final int start,
                                           final int end) {
    return l1norm(x,start,end) / Math.abs(sum(x,start,end)); }

  /** l1norm(x) / sum(x). */

  public static final double sumCondition (final double[] x) {
    return sumCondition(x,0,x.length); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private BigFractionAccumulator () {
    throw new UnsupportedOperationException("can't instantiate" +
      getClass().getSimpleName()); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
