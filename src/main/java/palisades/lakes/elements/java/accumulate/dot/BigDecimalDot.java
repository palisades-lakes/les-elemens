package palisades.lakes.elements.java.accumulate.dot;

import java.math.BigDecimal;

import palisades.lakes.elements.java.accumulate.AccumulatorBase;
import palisades.lakes.elements.java.accumulate.dot.BigDecimalDot;

//--------------------------------------------------------------
/** Exact (left-to-right) accumulation of doubles using a
 *  BigDecimal accumulator.
 *
 *  Note: BigDecimal.doubleValue() may not round correctly, to
 *  nearest, so this may not be exact!
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-16
 * @version 2017-05-17
 */

public final class BigDecimalDot extends AccumulatorBase {

  private BigDecimal s = new BigDecimal(0);

  @Override
  public final double doubleValue () {
    return this.s.doubleValue(); }

  @Override
  public final void add (final double z0,
                         final double z1) {
    // use constructor, rather than BigDecimal.valueOf(x), because
    // valueOf doesn't return the exact value, but rather the
    // BigDecimal corresponding to the printed decimal version of
    // zi.
    this.s = this.s
      .add(new BigDecimal(z0)
        .multiply(new BigDecimal(z1))); }

  @Override
  public final void addAll (final double[] z0,
                            final double[] z1,
                            final int start,
                            final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= z0.length;
    assert end <= z1.length;

    // use constructor, rather than BigDecimal.valueOf(x), because
    // valueOf doesn't return the exact value, but rather the
    // BigDecimal corresponding to the printed decimal version of
    // zi.

    // inline for performance?
    for (int i = start; i < end; i++) {
      this.s = this.s
        .add(new BigDecimal(z0[i])
          .multiply(new BigDecimal(z1[i]))); } }

  //--------------------------------------------------------------

  public static final double dot (final double[] z) {
    final BigDecimalDot a = BigDecimalDot.make();
    a.addAll(z);
    return a.doubleValue(); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private BigDecimalDot () { }

  public static final BigDecimalDot make () {
    return new BigDecimalDot(); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
