package palisades.lakes.elements.java.accumulate.sum;

import org.apache.commons.math3.fraction.BigFraction;

import palisades.lakes.elements.java.accumulate.AccumulatorBase;
import palisades.lakes.elements.java.accumulate.sum.BigFractionCondition;

//--------------------------------------------------------------
/** Exact (left-to-right) accumulation of doubles using a
 *  BigFraction accumulator.
 *
 *  Note: BigFraction.doubleValue() may not round correctly, to
 *  nearest, so this may not be exact!
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-18
 * @version 2017-05-19
 */

public final class BigFractionCondition extends AccumulatorBase {

  private BigFraction s = new BigFraction(0);
  private BigFraction l1 = new BigFraction(0);

  public final double sum () { return s.doubleValue(); }
  public final double l1norm () { return l1.doubleValue(); }

  @Override
  public final double doubleValue () {
    // divide doubles because s might be zero.
    return l1.doubleValue() / s.abs().doubleValue(); }

  @Override
  public final void add (final double z) {
    final BigFraction b = new BigFraction(z);
    s = s.add(b);
    l1 = l1.add(b.abs()); }

  @Override
  public final void addAll (final double[] z,
                            final int start,
                            final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= z.length;
    // inline for performance?
    for (int i = start; i < end; i++) {
      final BigFraction b = new BigFraction(z[i]);
      s = s.add(b);
      l1 = l1.add(b.abs()); } }

  @Override
  public final void addAll (final double[] z) {
    for (int i = 0; i < z.length; i++) {
      final BigFraction b = new BigFraction(z[i]);
      s = s.add(b);
      l1 = l1.add(b.abs()); } }

  //--------------------------------------------------------------

  public static final double condition (final double[] z) {
    final BigFractionCondition a = BigFractionCondition.make();
    a.addAll(z);
    return a.doubleValue(); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private BigFractionCondition () { }

  public static final BigFractionCondition make () {
    return new BigFractionCondition(); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
