package elements.java.accumulate.l1norm;

import org.apache.commons.math3.fraction.BigFraction;

import elements.java.accumulate.AccumulatorBase;

//--------------------------------------------------------------
/** Exact (left-to-right) accumulation of doubles using a
 *  BigFraction accumulator.
 *
 *  Note: BigFraction.doubleValue() may not round correctly, to
 *  nearest, so this may not be exact!
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-16
 * @version 2017-05-16
 */

public final class BigFractionL1norm extends AccumulatorBase {

  private BigFraction s = new BigFraction(0);

  @Override
  public final double doubleValue () {
    return this.s.doubleValue(); }

  @Override
  public final void add (final double z) {
    // use constructor, rather than BigFraction.valueOf(x), because
    // valueOf doesn't return the exact value, but rather the
    // BigFraction corresponding to the printed decimal version of
    // zi.
    this.s = this.s.add(new BigFraction(Math.abs(z))); }

  @Override
  public final void addAll (final double[] z,
                            final int start,
                            final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= z.length;

    // use constructor, rather than BigFraction.valueOf(x), because
    // valueOf doesn't return the exact value, but rather the
    // BigFraction corresponding to the printed decimal version of
    // zi.

    // inline for performance?
    for (int i = start; i < end; i++) {
      this.s = this.s.add(new BigFraction(Math.abs(z[i]))); } }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private BigFractionL1norm () { }

  public static final BigFractionL1norm make () {
    return new BigFractionL1norm(); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
