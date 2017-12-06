package elements.java.numbers.sum;

// ---------------------------------------------------------------
/** Compensated summation for lots of numbers. Only makes sense
 * for floating point numbers of various kinds.
 *
 * @see <a
 *      href="https://en.wikipedia.org/wiki/Kahan_summation_algorithm">
 *      Wikipedia:Kahan_summation_algorithm</a>
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-04-11
 * @version 2017-05-03
 */

public final class Kahan {

  // -------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final double sum (final float[] x,
                                  final int start,
                                  final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    double s = 0.0;
    double c = 0.0;
    for (int i=start;i<end;i++) {
      final double xi = x[i] - c;
      final double t = s + xi;
      c = (t - s) - xi;
      s = t; }
    return s; }

  /** Sum the elements of <code>x</code> .
   */

  public static final double sum (final float[] x) {
    return sum(x,0,x.length); }

  // -------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final double sum (final double[] x,
                                  final int start,
                                  final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    double s = 0.0;
    double c = 0.0;
    for (int i=start;i<end;i++) {
      final double xi = x[i] - c;
      final double t = s + xi;
      c = (t - s) - xi;
      s = t; }
    return s; }

  /** Sum the elements of <code>x</code> .
   */

  public static final double sum (final double[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final double sum (final Double[] x,
                                  final int start,
                                  final int end) {
    assert (0 <= start) : "Start must be non-negative" + start;
    assert (start < end);
    assert (end <= x.length);

    double s = 0.0;
    double c = 0.0;
    for (int i=start;i<end;i++) {
      final double xi = x[i].doubleValue() - c;
      final double t = s + xi;
      c = (t - s) - xi;
      s = t; }
    return s; }

  /** Sum the elements of <code>x</code> .
   */

  public static final double sum (final Double[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final double sum (final Float[] x,
                                  final int start,
                                  final int end) {
    assert (0 <= start) : "Start must be non-negative" + start;
    assert (start < end);
    assert (end <= x.length);

    double s = 0.0;
    double c = 0.0;
    for (int i=start;i<end;i++) {
      final double xi = x[i].doubleValue() - c;
      final double t = s + xi;
      c = (t - s) - xi;
      s = t; }
    return s; }

  /** Sum the elements of <code>x</code> .
   */

  public static final double sum (final Float[] x) {
    return sum(x,0,x.length); }

  // -------------------------------------------------------------
  // construction
  // -------------------------------------------------------------

  private Kahan () {
    throw new UnsupportedOperationException("can't instantiate" +
      getClass().getSimpleName()); }

  // -------------------------------------------------------------
} // end of class
// ---------------------------------------------------------------
