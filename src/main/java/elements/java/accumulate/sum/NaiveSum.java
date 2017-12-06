package elements.java.accumulate.sum;

import elements.java.accumulate.AccumulatorBase;

//--------------------------------------------------------------
/** Default (left-to-right) accumulation of sums.
 * Not a good choice for floating point numbers.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-04-11
 * @version 2017-05-16
 */

public final class NaiveSum extends AccumulatorBase {

  private double s = 0.0;

  //--------------------------------------------------------------

  @Override
  public final double doubleValue () { return this.s; }

  //--------------------------------------------------------------

  @Override
  public final void add (final double z) { this.s += z; }

  //--------------------------------------------------------------

  @Override
  public final void addAll (final double[] z,
                            final int start,
                            final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= z.length;

    for (int i = start; i < end; i++) { this.s += z[i]; } }

  //--------------------------------------------------------------

  public static final double sum (final double[] z) {
    final NaiveSum a = NaiveSum.make();
    a.addAll(z);
    return a.doubleValue(); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private NaiveSum () { }

  public static final NaiveSum make () { return new NaiveSum(); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
