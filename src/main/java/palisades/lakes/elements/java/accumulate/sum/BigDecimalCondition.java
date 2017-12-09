package palisades.lakes.elements.java.accumulate.sum;

import java.math.BigDecimal;

import palisades.lakes.elements.java.accumulate.AccumulatorBase;
import palisades.lakes.elements.java.accumulate.sum.BigDecimalCondition;

//--------------------------------------------------------------
/** Exact (left-to-right) accumulation of doubles using a
 *  BigDecimal accumulator.
 *
 *  Note: BigDecimal.doubleValue() may not round correctly, to
 *  nearest, so this may not be exact!
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-18
 * @version 2017-05-19
 */

public final class BigDecimalCondition extends AccumulatorBase {

  private BigDecimal s = new BigDecimal(0);
  private BigDecimal l1 = new BigDecimal(0);

  public final double sum () { return s.doubleValue(); }
  public final double l1norm () { return l1.doubleValue(); }

  @Override
  public final double doubleValue () {
    // divide doubles because s might be zero.
    return l1.doubleValue() / s.abs().doubleValue(); }

  @Override
  public final void add (final double z) {
    final BigDecimal b = new BigDecimal(z);
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
      final BigDecimal b = new BigDecimal(z[i]);
      s = s.add(b);
      l1 = l1.add(b.abs()); } }

  //--------------------------------------------------------------

  public static final double condition (final double[] z) {
    final BigDecimalCondition a = BigDecimalCondition.make();
    a.addAll(z);
    return a.doubleValue(); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private BigDecimalCondition () { }

  public static final BigDecimalCondition make () {
    return new BigDecimalCondition(); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
