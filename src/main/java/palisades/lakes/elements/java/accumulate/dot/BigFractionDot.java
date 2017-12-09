package palisades.lakes.elements.java.accumulate.dot;


import org.apache.commons.math3.fraction.BigFraction;

import palisades.lakes.elements.java.accumulate.AccumulatorBase;
import palisades.lakes.elements.java.accumulate.dot.BigFractionDot;

//--------------------------------------------------------------
/** Exact (left-to-right) accumulation of doubles using a
 *  BigFraction accumulator.
 *
 *  Note: BigFraction.doubleValue() may not round correctly, to
 *  nearest, so this may not be exact!
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-16
 * @version 2017-05-17
 */

public final class BigFractionDot extends AccumulatorBase {

  private BigFraction s = new BigFraction(0);

  @Override
  public final double doubleValue () {
    return this.s.doubleValue(); }

  @Override
  public final void add (final double z0,
                         final double z1) {
    // use constructor, rather than BigFraction.valueOf(x), because
    // valueOf doesn't return the exact value, but rather the
    // BigFraction corresponding to the printed decimal version of
    // zi.
    this.s = this.s
      .add(new BigFraction(z0)
        .multiply(new BigFraction(z1))); }

  @Override
  public final void addAll (final double[] z0,
                            final double[] z1,
                            final int start,
                            final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= z0.length;
    assert end <= z1.length;

    // use constructor, rather than BigFraction.valueOf(x), because
    // valueOf doesn't return the exact value, but rather the
    // BigFraction corresponding to the printed decimal version of
    // zi.

    // inline for performance?
    for (int i = start; i < end; i++) {
      this.s = this.s
        .add(new BigFraction(z0[i])
          .multiply(new BigFraction(z1[i]))); } }

  //--------------------------------------------------------------

  public static final double dot (final double[] z) {
    final BigFractionDot a = BigFractionDot.make();
    a.addAll(z);
    return a.doubleValue(); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private BigFractionDot () { }

  public static final BigFractionDot make () {
    return new BigFractionDot(); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
