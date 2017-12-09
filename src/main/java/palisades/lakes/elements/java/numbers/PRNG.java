package palisades.lakes.elements.java.numbers;

import java.math.BigDecimal;
import java.util.Arrays;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.sampling.ListSampler;

import palisades.lakes.elements.java.numbers.Doubles;

//----------------------------------------------------------------
/** Generate test data for sum algorithms.
 *
 * @see <a href="https://github.com/aseldawy/sumn">sumn, Ahmed Eldawy</a>
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-04-27
 * @version 2017-05-18
 */

public final class PRNG {

  //--------------------------------------------------------------

  public static final double[]
    randomDoublesNonNegative (final int n,
                              final int delta,
                              final UniformRandomProvider prng) {
    return Doubles.randomPositiveFinites(n,delta,prng); }

  public static final double[]
    randomDoublesNonNegative (final int n,
                              final UniformRandomProvider prng) {
    return Doubles.randomPositiveFinites(n,prng); }

  //--------------------------------------------------------------

  public static final double[]
    randomDoubles (final int n,
                   final int delta,
                   final UniformRandomProvider prng) {
    return Doubles.randomFinites(n,delta,prng); }

  public static final double[]
    randomDoubles (final int n,
                   final UniformRandomProvider prng) {
    return Doubles.randomFinites(n,prng); }

  //--------------------------------------------------------------
  /** Anderson's ill conditioned data */

  public static final double[]
    randomDoublesMinusMean (final int n,
                            final int delta,
                            final UniformRandomProvider prng) {

    final double[] x = randomDoubles(n,delta,prng);

    // 'exact' mean
    BigDecimal sum = new BigDecimal(0);
    for (int i = 0; i < n; i++) {
      sum = sum.add(new BigDecimal(x[i])); }

    final double mean = sum.divide(new BigDecimal(n)).doubleValue();

    for (int i = 0; i < n; i++) { x[i] -= mean; }

    return x; }

  public static final double[]
    randomDoublesMinusMean (final int n,
                            final UniformRandomProvider prng) {

    final double[] x = randomDoubles(n,prng);

    // 'exact' mean
    BigDecimal sum = new BigDecimal(0);
    for (int i = 0; i < n; i++) {
      sum = sum.add(new BigDecimal(x[i])); }

    final double mean = sum.divide(new BigDecimal(n)).doubleValue();

    for (int i = 0; i < n; i++) { x[i] -= mean; }

    return x; }

  //--------------------------------------------------------------

  public static final double[]
    randomDoublesZeroSum (final int n,
                          final int delta,
                          final UniformRandomProvider prng) {

    final double[] x = new double[n];
    for (int i = 0; i < (n/2); i+=2) {
      final double xi = Doubles.randomFinite(delta, prng);
      x[i] = xi;
      x[i+1] = -xi;}
    ListSampler.shuffle(prng,Arrays.asList(x));
    return x; }

  public static final double[]
    randomDoublesZeroSum (final int n,
                          final UniformRandomProvider prng) {

    final double[] x = new double[n];
    for (int i = 0; i < (n/2); i+=2) {
      final double xi = Doubles.randomFinite(prng);
      x[i] = xi;
      x[i+1] = -xi;}
    ListSampler.shuffle(prng,Arrays.asList(x));
    return x; }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private PRNG () {
    super();
    throw new
    UnsupportedOperationException("can't instantiate " +
      this.getClass()); }

  //--------------------------------------------------------------
} // end class
//--------------------------------------------------------------

