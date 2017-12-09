package palisades.lakes.elements.java.numbers;

import org.apache.commons.rng.UniformRandomProvider;

//----------------------------------------------------------------
/** Utilities for doubles.
 * <p>
 * <a href="http://perso.ens-lyon.fr/jean-michel.muller/Handbook.html" >
 * Muller et al, "Handbook of floating-point arithmetic".</a>
 * <p>
 * <a href="http://www.ma.man.ac.uk/~higham/asna/" >
 * Higham, "Accuracy and stability of numerical algorithms".</a>
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-03
 * @version 2017-05-13
 */

public final class Doubles {

  //--------------------------------------------------------------

  public static final int SIGN_BITS = 1;
  public static final int EXPONENT_BITS = 11;
  public static final int SIGNIFICAND_BITS = 52;

  public static final long SIGN_MASK =
    1L << (EXPONENT_BITS + SIGNIFICAND_BITS);

  public static final long EXPONENT_MASK =
    ((1L << EXPONENT_BITS) - 1L) << SIGNIFICAND_BITS;

  public static final long SIGNIFICAND_MASK =
    (1L << SIGNIFICAND_BITS) - 1L;

  public static final int EXPONENT_BIAS =
    (1 << (EXPONENT_BITS - 1)) - 1;

  public static final int MAXIMUM_BIASED_EXPONENT =
    (1 << EXPONENT_BITS) - 1;

  public static final int MAXIMUM_EXPONENT =
    EXPONENT_BIAS;

  public static final int MINIMUM_NORMAL_EXPONENT =
    1 - MAXIMUM_EXPONENT;

  public static final int MINIMUM_SUBNORMAL_EXPONENT =
    MINIMUM_NORMAL_EXPONENT - SIGNIFICAND_BITS;

  // eclipse validates constant expressions at build time
  //  static {
  //    assert ((~0L) == (SIGN_MASK | EXPONENT_MASK | SIGNIFICAND_MASK));
  //    assert (0L == (SIGN_MASK & EXPONENT_MASK));
  //    assert (0L == (EXPONENT_MASK & SIGNIFICAND_MASK));
  //    assert (0L == (SIGN_MASK & SIGNIFICAND_MASK));
  //  }
  //--------------------------------------------------------------

  public static final long significand (final double x) {
    return
      SIGNIFICAND_MASK
      &
      Double.doubleToRawLongBits(x); }

  //--------------------------------------------------------------

  public static final int biasedExponent (final double x) {
    return
      (int)
      ((EXPONENT_MASK
        &
        Double.doubleToRawLongBits(x))
        >> SIGNIFICAND_BITS); }

  //--------------------------------------------------------------

  public static final int unbiasedExponent (final double x) {
    return biasedExponent(x) - EXPONENT_BIAS; }

  //--------------------------------------------------------------

  public static final int signBit (final double x) {
    return  (int)
      ((SIGN_MASK
        &
        Double.doubleToRawLongBits(x))
        >> (EXPONENT_BITS + SIGNIFICAND_BITS)); }

  public static final boolean nonNegative (final double x) {
    return  0 == signBit(x); }

  //--------------------------------------------------------------

  public static final boolean isNormal (final double x) {
    final int be = biasedExponent(x);
    return (0.0 == x) || ((0 != be) && (0x7ff != be)); }

  //--------------------------------------------------------------

  public static final double makeDouble (final int s,
                                         final int e,
                                         final long t) {
    assert ((0 == s) || (1 ==s)) : "Invalid sign bit:" + s;
    assert (0 <= e) :
      "Negative exponent:" + Integer.toHexString(e);
    assert (e <= MAXIMUM_BIASED_EXPONENT) :
      "Exponent too large:" + Integer.toHexString(e) +
      ">" + Integer.toHexString(MAXIMUM_BIASED_EXPONENT);
    assert (0 <= t) :
      "Negative significand:" + Long.toHexString(t);
    assert (t <= SIGNIFICAND_MASK) :
      "Significand too large:" + Long.toHexString(t) +
      ">" + Long.toHexString(SIGNIFICAND_MASK);

    final long ss = ((long) s) << (EXPONENT_BITS + SIGNIFICAND_BITS);
    final long se = ((long) e) << SIGNIFICAND_BITS;

    assert (0L == (ss & se & t));
    return Double.longBitsToDouble(ss | se | t); }

  //--------------------------------------------------------------
  // TODO: unify the methods below by passing ranges of sign bit,
  // biased exponent, and signifcand.
  //--------------------------------------------------------------

  public static final double
  randomDouble (final int delta,
                final UniformRandomProvider prng) {
    return
      makeDouble(prng.nextInt(2),
        prng.nextInt(delta),
        prng.nextLong() & SIGNIFICAND_MASK); }

  public static final double
  randomDouble (final UniformRandomProvider prng) {
    return randomDouble(1+MAXIMUM_BIASED_EXPONENT,prng); }

  public static final double[]
    randomDoubles (final int n,
                   final UniformRandomProvider prng) {
    final double[] z = new double[n];
    for (int i=0;i<n;i++) { z[i] = randomDouble(prng); }
    return z; }

  //--------------------------------------------------------------

  public static final double
  randomFinite (final int delta,
                final UniformRandomProvider prng) {
    assert (delta <= MAXIMUM_BIASED_EXPONENT) :
      Integer.toHexString(delta) + " > " +
      Integer.toHexString(MAXIMUM_BIASED_EXPONENT);
    return makeDouble(prng.nextInt(2),
      prng.nextInt(delta),
      prng.nextLong() & SIGNIFICAND_MASK); }

  // Note: high probability sum will overflow.

  public static final double
  randomFinite (final UniformRandomProvider prng) {
    return randomFinite(MAXIMUM_BIASED_EXPONENT,prng); }

  public static final double[]
    randomFinites (final int n,
                   final int delta,
                   final UniformRandomProvider prng) {
    final double[] z = new double[n];
    for (int i=0;i<n;i++) { z[i] = randomFinite(delta,prng); }
    return z; }

  // TODO: work out probability of overflow as function of n and
  // delta to get worse expected condition with low overflow prob.

  public static final double[]
    randomFinites (final int n,
                   final UniformRandomProvider prng) {
    final int delta = biasedExponent(Double.MAX_VALUE/n);
    //  System.out.println("n=" + n + ", delta=" + delta);
    //  System.out.flush();
    return randomFinites(n,delta,prng); }

  //--------------------------------------------------------------

  public static final double
  randomPositiveFinite (final int delta,
                        final UniformRandomProvider prng) {
    assert (delta <= MAXIMUM_BIASED_EXPONENT) :
      Integer.toHexString(delta) + " > " +
      Integer.toHexString(MAXIMUM_BIASED_EXPONENT);
    final double z =
      makeDouble(0,
        prng.nextInt(delta),
        prng.nextLong() & SIGNIFICAND_MASK);
    assert Double.isFinite(z);
    return z; }

  // Note: sum of these will overflow quickly.
  public static final double
  randomPositiveFinite (final UniformRandomProvider prng) {
    return randomPositiveFinite(MAXIMUM_BIASED_EXPONENT,prng); }


  public static final double[]
    randomPositiveFinites (final int n,
                           final int delta,
                           final UniformRandomProvider prng) {
    final double[] z = new double[n];
    for (int i=0;i<n;i++) {
      z[i] = randomPositiveFinite(delta,prng);
      assert Double.isFinite(z[i]); }
    return z; }

  /** Compute exponent range so that sum is unlikely to overflow.
   */
  public static final double[]
    randomPositiveFinites (final int n,
                           final UniformRandomProvider prng) {
    final int delta = biasedExponent(Double.MAX_VALUE/n);
    //    System.out.println("n=" + n + ", delta=" + delta);
    //    System.out.flush();
    return randomPositiveFinites(n,delta,prng); }

  //--------------------------------------------------------------

  public static final double
  randomPositiveDouble (final int delta,
                        final UniformRandomProvider prng) {
    return
      makeDouble(0,
        prng.nextInt(delta),
        prng.nextLong() & SIGNIFICAND_MASK); }

  public static final double
  randomPositiveDouble (final UniformRandomProvider prng) {
    return randomPositiveDouble(1+MAXIMUM_BIASED_EXPONENT,prng); }

  public static final double[]
    randomPositiveDoubles (final int n,
                           final UniformRandomProvider prng) {
    final double[] z = new double[n];
    for (int i=0;i<n;i++) { z[i] = randomPositiveDouble(prng); }
    return z; }

  //--------------------------------------------------------------

  public static final double
  randomSubnormal (final UniformRandomProvider prng) {
    return
      makeDouble(prng.nextInt(2),
        0,
        prng.nextLong() & SIGNIFICAND_MASK); }

  public static final double[]
    randomSubnormals (final int n,
                      final UniformRandomProvider prng) {
    final double[] z = new double[n];
    for (int i=0;i<n;i++) { z[i] = randomSubnormal(prng); }
    return z; }

  //--------------------------------------------------------------

  public static final double
  randomNonfinite (final UniformRandomProvider prng) {
    return
      makeDouble(prng.nextInt(2),
        MAXIMUM_BIASED_EXPONENT,
        prng.nextLong() & SIGNIFICAND_MASK); }

  public static final double[]
    randomNonfinites (final int n,
                      final UniformRandomProvider prng) {
    final double[] z = new double[n];
    for (int i=0;i<n;i++) { z[i] = randomNonfinite(prng); }
    return z; }

  //--------------------------------------------------------------

  public static final boolean isHalfUlp (final double x) {
    // TODO: do we need to check for NaN and infinity?
    return (0.0 != x) && (0L == significand(x)); }

  //--------------------------------------------------------------

  public static final double halfUlp (final double x) {
    // TODO: do we need to check for NaN and infinity?
    // TODO: compare to c++ implementation
    // TODO: return zero when x is zero?
    if (0.0 == x) { return 0.0; }
    return 0.5 * Math.ulp(x); }

  //--------------------------------------------------------------
  /** Return correctly rounded sum of 3 non-overlapping doubles.
   * <p>
   * See <a href="https://github.com/Jeffrey-Sarnoff/IFastSum.jl" >
   * IFastSum.jl</a> (visited 2017-05-01, MIT License)
   */

  public static final double round3 (final double s0,
                                     final double s1,
                                     final double s2) {

    // non-overlapping here means:
    //    assert Math.abs(s0) > Math.abs(s1);
    //    assert Math.abs(s1) > Math.abs(s2) :
    //      Double.toHexString(s1) + " <= " + Double.toHexString(s2);
    assert s0 == (s0 + s1) :
      Double.toHexString(s0) + " + " + Double.toHexString(s1) +
      " -> " + Double.toHexString(s0+s1);
    assert s1 == (s1 + s2);

    if ((isHalfUlp(s1)) &&
      (Math.signum(s1) == Math.signum(s2))) {
      return s0 + Math.nextUp(s1); }
    return s0; }

  //--------------------------------------------------------------
  // TODO: faster to update or create a new instance?

  public static final class Add2 {

    public double z0 = 0.0;
    public double z1 = 0.0;

    public final void noBranch (final double a, final double b) {
      assert Double.isFinite(a) && Double.isFinite(b) :
        Double.toHexString(a) + " + " + Double.toHexString(b);
      final double x = a + b;
      final double z = x - a;
      z1 = (a - (x - z)) + (b - z);
      z0 = x;
      assert Double.isFinite(z0) && Double.isFinite(z1) :
        Double.toHexString(z0) + " + " + Double.toHexString(z1); }

    public final void branch (final double a, final double b) {
      assert Double.isFinite(a) && Double.isFinite(b) :
        Double.toHexString(a) + " + " + Double.toHexString(b);
      final double x = a + b;
      z0 = x;
      if (biasedExponent(a) > biasedExponent(b)) {
        z1 = b - (x - a);}
      else {
        z1 = a - (x - b);  }
      assert Double.isFinite(z0) && Double.isFinite(z1) :
        Double.toHexString(z0) + " + " + Double.toHexString(z1); }

    public Add2 () {} }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private Doubles () {
    throw new UnsupportedOperationException("can't instantiate" +
      this.getClass()); }

  //--------------------------------------------------------------
} // end of class
//----------------------------------------------------------------
