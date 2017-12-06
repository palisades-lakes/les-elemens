package elements.java.numbers.sum;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.math3.fraction.BigFraction;

import clojure.lang.Numbers;
import clojure.lang.Ratio;

//----------------------------------------------------------------
/** Recursive pairwise summation of lots of numbers.
 * Accuracy advantages for floating points numbers.
 * Possible speed advantages in all cases.
 * (TODO: why is it faster?)
 *
 * @see <a
 *      href="https://en.wikipedia.org/wiki/Pairwise_summation">
 *      Wikipedia:Pairwise_summation</a>
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-04-11
 * @version 2017-05-03
 */

public final class Pairwise {

  //--------------------------------------------------------------
  // TODO: this is arbitrary. Implement accuracy speed benchmark
  // to justify.
  // TODO: add cutoff arg to permit user to specify?

  // https://github.com/numpy/numpy/blob/master/numpy/core/src/umath/loops.c.src
  // #define PW_BLOCKSIZE    128
  // TODO: different bounds for float vs double vs ?

  private static final int PAIRWISE_NAIVE_BOUND = 128;

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   *
   */

  public static final long sum (final byte[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return sum(x,start,split) + sum(x,split,end); }

  /** Sum the elements of <code>x</code>.
   */

  public static final long sum (final byte[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   *
   */

  public static final long sum (final short[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return sum(x,start,split) + sum(x,split,end); }

  /** Sum the elements of <code>x</code>.
   */

  public static final long sum (final short[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   *
   */

  public static final long sum (final int[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return sum(x,start,split) + sum(x,split,end); }

  /** Sum the elements of <code>x</code>.
   */

  public static final long sum (final int[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final long sum (final long[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return sum(x,start,split) + sum(x,split,end); }

  /** Sum the elements of <code>x</code> .
   */

  public static final double sum (final long[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   *
   */

  public static final long sum (final Byte[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return sum(x,start,split) + sum(x,split,end); }

  /** Sum the elements of <code>x</code>.
   */

  public static final long sum (final Byte[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   *
   */

  public static final long sum (final Short[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return sum(x,start,split) + sum(x,split,end); }

  /** Sum the elements of <code>x</code>.
   */

  public static final long sum (final Short[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   *
   */

  public static final long sum (final Integer[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return sum(x,start,split) + sum(x,split,end); }

  /** Sum the elements of <code>x</code>.
   */

  public static final long sum (final Integer[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final long sum (final Long[] x,
                                final int start,
                                final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return sum(x,start,split) + sum(x,split,end); }

  /** Sum the elements of <code>x</code> .
   */

  public static final double sum (final Long[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   */

  public static final double sum (final float[] x,
                                  final int start,
                                  final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return sum(x,start,split) + sum(x,split,end); }

  /**
   * Sum the elements of <code>x</code> .
   *
   * @see <a
   *      href="https://en.wikipedia.org/wiki/Pairwise_summation">
   *      Wikipedia:Pairwise_summation</a>
   */

  public static final double sum (final float[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /**
   * Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   *
   * @see <a
   *      href="https://en.wikipedia.org/wiki/Pairwise_summation">
   *      Wikipedia:Pairwise_summation</a>
   */

  public static final double sum (final double[] x,
                                  final int start,
                                  final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return sum(x,start,split) + sum(x,split,end); }

  /**
   * Sum the elements of <code>x</code> .
   *
   * @see <a
   *      href="https://en.wikipedia.org/wiki/Pairwise_summation">
   *      Wikipedia:Pairwise_summation</a>
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
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return sum(x,start,split) + sum(x,split,end); }

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
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return sum(x,start,split) + sum(x,split,end); }

  /** Sum the elements of <code>x</code> .
   */

  public static final double sum (final Float[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   * Accumulate as a Number.
   */

  public static final BigDecimal sum (final BigDecimal[] x,
                                      final int start,
                                      final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return sum(x,start,split).add(sum(x,split,end)); }

  /** Sum the elements of <code>x</code> .
   */

  public static final BigDecimal sum (final BigDecimal[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   * Accumulate as a Number.
   */

  public static final BigFraction sum (final BigFraction[] x,
                                       final int start,
                                       final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return sum(x,start,split).add(sum(x,split,end)); }

  /** Sum the elements of <code>x</code> .
   */

  public static final BigFraction sum (final BigFraction[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   * Accumulate as a Number.
   */

  public static final Ratio sum (final Ratio[] x,
                                 final int start,
                                 final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return Numbers.toRatio(Numbers.add(sum(x,start,split),
      sum(x,split,end))); }

  /** Sum the elements of <code>x</code> .
   */

  public static final Ratio sum (final Ratio[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   * Accumulate as a Number.
   */

  public static final Number sum (final Number[] x,
                                  final int start,
                                  final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return Add.add(sum(x,start,split),sum(x,split,end)); }

  /** Sum the elements of <code>x</code> .
   */

  public static final Number sum (final Number[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   * Accumulate as a Number.
   */

  public static final Number sum (final Object[] x,
                                  final int start,
                                  final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.length;

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return Add.add(sum(x,start,split),sum(x,split,end)); }

  /** Sum the elements of <code>x</code> .
   */

  public static final Number sum (final Object[] x) {
    return sum(x,0,x.length); }

  //--------------------------------------------------------------
  /** Sum the elements of <code>x</code> from <code>start</code>
   * (inclusive) to <code>end</code> (exclusive).
   * Accumulate as a Number.
   */

  public static final Number sum (final List x,
                                  final int start,
                                  final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= x.size();

    final int n = end - start;
    if (n <= PAIRWISE_NAIVE_BOUND) {
      return Naive.sum(x,start,end); }
    final int split = start + (n / 2);
    return Add.add(sum(x,start,split),sum(x,split,end)); }

  /** Sum the elements of <code>x</code> .
   */

  public static final Number sum (final List x) {
    return sum(x,0,x.size()); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private Pairwise () {
    throw new UnsupportedOperationException("can't instantiate" +
      getClass().getSimpleName()); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
