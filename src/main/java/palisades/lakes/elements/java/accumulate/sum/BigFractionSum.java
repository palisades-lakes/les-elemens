package palisades.lakes.elements.java.accumulate.sum;

import org.apache.commons.math3.fraction.BigFraction;

import palisades.lakes.elements.java.accumulate.AccumulatorBase;
import palisades.lakes.elements.java.accumulate.sum.BigFractionSum;

//--------------------------------------------------------------
/** Exact (left-to-right) accumulation of doubles using a
 *  BigFraction accumulator.
 *
 *  Note: BigFraction.doubleValue() may not round correctly, to
 *  nearest, so this may not be exact!
 *
 * @author palisades dot lakes at gmail dot com
 * @since 2017-05-16
 * @version 2017-05-16
 */

public final class BigFractionSum
extends AccumulatorBase {

  private BigFraction s = new BigFraction(0);

  @Override
  public final double doubleValue () {
    return this.s.doubleValue(); }

  @Override
  public final void add (final double z) {
    this.s = this.s.add(new BigFraction(z)); }

  @Override
  public final void addAll (final double[] z,
                            final int start,
                            final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= z.length;

    for (int i = start; i < end; i++) {
      this.s = this.s.add(new BigFraction(z[i])); } }

  //--------------------------------------------------------------

  public static final double sum (final double[] z) {
    final BigFractionSum a = BigFractionSum.make();
    a.addAll(z);
    return a.doubleValue(); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private BigFractionSum () { }

  public static final BigFractionSum make () {
    return new BigFractionSum(); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
