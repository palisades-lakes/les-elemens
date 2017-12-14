package palisades.lakes.elements.java.numbers.sum;

import java.math.BigDecimal;

//--------------------------------------------------------------
/** Exact (left-to-right) accumulation of doubles using a
 *  BigDecimal accumulator.
 *
 *  Note: BigDecimal.doubleValue() may not round correctly, to
 *  nearest, so this may not be exact!
 *
 * @author palisades dot lakes at gmail dot com
 * @since 2017-05-12
 * @version 2017-05-18
 */

public final class BigDecimalAccumulator {

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

    // use constructor, rather than BigDecimal.valueOf(x), because
    // valueOf doesn't return the exact value, but rather the
    // BigDecimal corresponding to the printed decimal version of
    // x

    BigDecimal s = new BigDecimal(x[start]);
    for (int i = start + 1; i < end; i++) {
      s = s.add(new BigDecimal(x[i])); }
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

    // use constructor, rather than BigDecimal.valueOf(x), because
    // valueOf doesn't return the exact value, but rather the
    // BigDecimal corresponding to the printed decimal version of
    // x

    BigDecimal s = new BigDecimal(x[start]);
    for (int i = start + 1; i < end; i++) {
      s = s.add(new BigDecimal(Math.abs(x[i]))); }
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

  private BigDecimalAccumulator () {
    throw new UnsupportedOperationException("can't instantiate" +
      getClass().getSimpleName()); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
