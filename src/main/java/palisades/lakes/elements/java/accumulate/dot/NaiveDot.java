package palisades.lakes.elements.java.accumulate.dot;

import palisades.lakes.elements.java.accumulate.AccumulatorBase;
import palisades.lakes.elements.java.accumulate.dot.NaiveDot;

//--------------------------------------------------------------
/** Default (left-to-right) accumulation of sums.
 * Not a good choice for floating point numbers.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-17
 * @version 2017-05-17
 */

public final class NaiveDot extends AccumulatorBase {

  private double s = 0.0;

  //--------------------------------------------------------------

  @Override
  public final double doubleValue () { return this.s; }

  //--------------------------------------------------------------

  @Override
  public final void add (final double z0,
                         final double z1) {
    this.s += z0*z1; }

  //--------------------------------------------------------------

  @Override
  public final void addAll (final double[] z0,
                            final double[] z1,
                            final int start,
                            final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= z0.length;
    assert end <= z1.length;

    for (int i = start; i < end; i++) {
      this.s += z0[i]*z1[i]; } }

  //--------------------------------------------------------------

  public static final double dot (final double[] z0,
                                  final double[] z1) {
    final NaiveDot a = NaiveDot.make();
    a.addAll(z0,z1);
    return a.doubleValue(); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private NaiveDot () { }

  public static final NaiveDot make () { return new NaiveDot(); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
