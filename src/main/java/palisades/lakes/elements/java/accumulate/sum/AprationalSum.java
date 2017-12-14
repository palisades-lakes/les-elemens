package palisades.lakes.elements.java.accumulate.sum;

import java.math.RoundingMode;

import org.apache.commons.math3.fraction.BigFraction;
import org.apfloat.Apint;
import org.apfloat.Aprational;
import org.apfloat.AprationalMath;

import palisades.lakes.elements.java.accumulate.AccumulatorBase;
import palisades.lakes.elements.java.accumulate.sum.AprationalSum;

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

public final class AprationalSum
extends AccumulatorBase {

  private Aprational s = new Aprational("0",2);

  private static final Aprational toAprational (final double z) {
    final BigFraction bf = new BigFraction(z);
    final Apint d = new Apint(bf.getDenominator());
    final Apint n = new Apint(bf.getNumerator());
    return new Aprational(n,d); }
  
  @Override
  public final double doubleValue () {
    // pseudo binary128
    return AprationalMath.round(
      this.s,
      113L,
      RoundingMode.HALF_EVEN).doubleValue(); }

  @Override
  public final void add (final double z) {
    this.s = this.s.add(toAprational(z)); }

  @Override
  public final void addAll (final double[] z,
                            final int start,
                            final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= z.length;

    for (int i = start; i < end; i++) {
      this.s = this.s.add(toAprational(z[i])); } }

  //--------------------------------------------------------------

  public static final double sum (final double[] z) {
    final AprationalSum a = AprationalSum.make();
    a.addAll(z);
    return a.doubleValue(); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private AprationalSum () { }

  public static final AprationalSum make () {
    return new AprationalSum(); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
