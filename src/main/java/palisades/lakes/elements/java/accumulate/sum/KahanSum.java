package palisades.lakes.elements.java.accumulate.sum;

import palisades.lakes.elements.java.accumulate.AccumulatorBase;
import palisades.lakes.elements.java.accumulate.sum.KahanSum;

//----------------------------------------------------------------
/** Compensated summation for lots of numbers. Only makes sense
 * for floating point numbers of various kinds.
 *
 * @see <a
 *      href="https://en.wikipedia.org/wiki/Kahan_summation_algorithm">
 *      Wikipedia:Kahan_summation_algorithm</a>
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-04-11
 * @version 2017-05-16
 */

public final class KahanSum extends AccumulatorBase {

  private double s = 0.0;
  private double c = 0.0;

  //--------------------------------------------------------------

  @Override
  public final double doubleValue () { return this.s; }

  //--------------------------------------------------------------

  @Override
  public final void add (final double z) {
    final double zi = z - c;
    final double t = s + zi;
    c = (t - s) - zi;
    s = t; }

  //--------------------------------------------------------------

  @Override
  public final void addAll (final double[] z,
                            final int start,
                            final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= z.length;

    for (int i=start;i<end;i++) {
      final double zi = z[i] - c;
      final double t = s + zi;
      c = (t - s) - zi;
      s = t; } }

  //--------------------------------------------------------------

  public static final double sum (final double[] z) {
    final KahanSum a = KahanSum.make();
    a.addAll(z);
    return a.doubleValue(); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private KahanSum () { }

  public static final KahanSum make () { return new KahanSum(); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
