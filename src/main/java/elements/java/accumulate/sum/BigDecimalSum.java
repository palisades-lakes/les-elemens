package elements.java.accumulate.sum;

import java.math.BigDecimal;

import elements.java.accumulate.AccumulatorBase;

//--------------------------------------------------------------
/** Exact (left-to-right) accumulation of doubles using a
 *  BigDecimal accumulator.
 *
 *  Note: BigDecimal.doubleValue() may not round correctly, to
 *  nearest, so this may not be exact!
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-16
 * @version 2017-05-16
 */

public final class BigDecimalSum extends AccumulatorBase {

  private BigDecimal s = new BigDecimal(0);

  @Override
  public final double doubleValue () {
    return this.s.doubleValue(); }

  @Override
  public final void add (final double z) {
    // use constructor, rather than BigDecimal.valueOf(x), because
    // valueOf doesn't return the exact value, but rather the
    // BigDecimal corresponding to the printed decimal version of
    // zi.
    this.s = this.s.add(new BigDecimal(z)); }

  @Override
  public final void addAll (final double[] z,
                            final int start,
                            final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= z.length;

    // use constructor, rather than BigDecimal.valueOf(x), because
    // valueOf doesn't return the exact value, but rather the
    // BigDecimal corresponding to the printed decimal version of
    // zi.

    // inline for performance?
    for (int i = start; i < end; i++) {
      this.s = this.s.add(new BigDecimal(z[i])); } }

  //--------------------------------------------------------------

  public static final double sum (final double[] z) {
    final BigDecimalSum a = BigDecimalSum.make();
    a.addAll(z);
    return a.doubleValue(); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private BigDecimalSum () { }

  public static final BigDecimalSum make () {
    return new BigDecimalSum(); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
