package palisades.lakes.elements.java.accumulate.sum;

import java.math.BigDecimal;
import java.math.BigInteger;

import clojure.lang.Numbers;
import clojure.lang.Ratio;
import palisades.lakes.elements.java.accumulate.AccumulatorBase;
import palisades.lakes.elements.java.accumulate.sum.RatioSum;

//--------------------------------------------------------------
/** Exact (left-to-right) accumulation of doubles using a
 *  BigFraction accumulator.
 *
 *  Note: BigFraction.doubleValue() may not round correctly, to
 *  nearest, so this may not be exact!
 *
 * @author palisades dot lakes at gmail dot com
 * @since 2017-05-24
 * @version 2017-05-24
 */

public final class RatioSum
extends AccumulatorBase {

  private Number s = new Ratio(BigInteger.ZERO,BigInteger.ONE);

  
  @Override
  public final double doubleValue () {
    return Numbers.toRatio(this.s).decimalValue().doubleValue(); }

  @Override
  public final void add (final double z) {
    // use constructor, rather than BigDecimal.valueOf(z), because
    // valueOf doesn't return the exact value, but rather the
    // BigDecimal corresponding to the printed decimal version of
    // zi.
    this.s = Numbers.add(
      this.s,
      Numbers.toRatio(new BigDecimal(z))); }

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
      this.s = 
        Numbers.add(
          this.s,
          Numbers.toRatio(new BigDecimal(z[i]))); } }

  //--------------------------------------------------------------

  public static final double sum (final double[] z) {
    final RatioSum a = RatioSum.make();
    a.addAll(z);
    return a.doubleValue(); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private RatioSum () { }

  public static final RatioSum make () {
    return new RatioSum(); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
