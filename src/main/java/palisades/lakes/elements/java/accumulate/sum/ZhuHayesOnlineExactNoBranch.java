package palisades.lakes.elements.java.accumulate.sum;

import java.util.Arrays;

import palisades.lakes.elements.java.accumulate.AccumulatorBase;
import palisades.lakes.elements.java.numbers.Doubles;
import palisades.lakes.elements.java.numbers.sum.IFastSum;

//----------------------------------------------------------------
/** Fast exact online summation. Basic idea is to use a separate
 * accumulator for each (biased) exponent value.
 * <p>
 * Primary reference:
 * <p>
 * <a href="http://dl.acm.org/citation.cfm?id=1824815" >
 * Yong Kang Zhu and Wayne B. Hayes,
 * "Algorithm 908: Online Exact Summation of Floating-Point Streams",
 * {ACM} TOMS Volume 37 Issue 3, September 2010</a>
 * <p>
 * Also see:
 * <p>
 * <a href="https://macsphere.mcmaster.ca/bitstream/11375/9258/1/fulltext.pdf" >
 * Yuhang Zhao, "Some Highly Accurate Basic Linear Algebra Subroutines",
 * MS Thesis, McMaster U , Computing and Software, 2010.</a>
 * <p>
 * <a href="http://epubs.siam.org/doi/abs/10.1137/070710020?journalCode=sjoce3" >
 * Yong Kang Zhu and Wayne B. Hayes,
 * "Correct Rounding and a Hybrid Approach to Exact Floating-Point Summation,"
 * {SIAM} J. Sci. Comput.,  31(4), p 2981–3001, Jun 2009. (21 pages)</a>
 * <p>
 * This implementation based on the code with TOMS 908 and:
 * <p>
 * <a href="https://github.com/bsteinb/accurate/blob/master/src/sum/onlineexactsum.rs">
 * Benedikt Steinbusch,
 * "(More or less) accurate floating point algorithms"</a>
 * (Apache 2.0 or MIT license, visited 2017-05-01)
 * <p>
 * <em>NOT</em> thread safe!
 * <p>
 * @author palisades dot lakes at gmail dot com
 * @since 2017-04-27
 * @version 2017-06-02
 */

public final class ZhuHayesOnlineExactNoBranch
extends AccumulatorBase {

  //--------------------------------------------------------------

  private static final int biasedExponent (final double x) {
    return
      (int)
      ((Doubles.EXPONENT_MASK
        &
        Double.doubleToRawLongBits(x))
        >> Doubles.SIGNIFICAND_BITS); }

  //  private static final boolean greaterExponent (final double z0,
  //                                                final double z1) {
  //    return
  //      (Doubles.EXPONENT_MASK & Double.doubleToRawLongBits(z0))
  //      >
  //    (Doubles.EXPONENT_MASK & Double.doubleToRawLongBits(z1)); }

  //--------------------------------------------------------------
  // TODO: faster to update or create a new instance?

  private static final class Add2 {

    private double z0 = 0.0;
    private double z1 = 0.0;

    private final void add2 (final double a, final double b) {
      z0 = a + b;
      final double z = z0 - a;
      z1 = (a - (z0 - z)) + (b - z); }

    private Add2 () {} }

  //--------------------------------------------------------------

  private static final int NADDS =
    1 << (Doubles.SIGNIFICAND_BITS / 2);

  private static final int NACCUMULATORS =
    1 << Doubles.EXPONENT_BITS;

  //--------------------------------------------------------------

  private int i;
  private double[] a1;
  private double[] a2;
  private double[] b1v;
  private double[] b2v;
  private final double[] ifastinput;
  private final Add2 add2 = new Add2();

  //--------------------------------------------------------------

  private final void compact () {
    // preallocated
    // Step 4(6)(a)
    //final double[] b1v = new double[NACCUMULATORS];
    //final double[] b2v = new double[NACCUMULATORS];

    // Step 4(6)(b)
    for (final double y : a2) {
      // Step 4(6)(b)(i)
      final int j = biasedExponent(y);
      // These accesses are guaranteed to be within bounds, because:
      //assert (b1v.length == NACCUMULATORS);
      //assert (b2v.length == NACCUMULATORS);
      //assert (j < NACCUMULATORS);
      // Step 4(6)(b)(ii)
      add2.add2(b1v[j],y);
      b1v[j] = add2.z0;
      // Step 4(6)(b)(iii)
      b2v[j] += add2.z1; }

    // Step 4(6)(c)
    // swap instead of gc
    final double[] tmp1 = a1;
    final double[] tmp2 = a2;
    a1 = b1v;
    a2 = b2v;
    b1v = tmp1;
    b2v = tmp2;
    Arrays.fill(b1v,0.0);
    Arrays.fill(b2v,0.0);

    // Step 4(6)(d)
    i = 2 * NACCUMULATORS; }

  //--------------------------------------------------------------
  // aka zero()

  //  public final void clear () {
  //    i = 0;
  //    Arrays.fill(a1,0.0);
  //    Arrays.fill(a2,0.0);
  //    Arrays.fill(b1v,0.0);
  //    Arrays.fill(b2v,0.0); }

  //--------------------------------------------------------------
  // aka sum()

  @Override
  public final double doubleValue () {
    // Step 5
    // TODO: is it worth dropping zeros?
    //a.retain(|&x| x != F::zero());
    System.arraycopy(a1,0,ifastinput,0,NACCUMULATORS);
    System.arraycopy(a2,0,ifastinput,NACCUMULATORS,NACCUMULATORS);

    // Step 6
    return IFastSum.destructiveSum(ifastinput); }

  //--------------------------------------------------------------
  // aka AddAssign

  @Override
  public final void add (final double z) {
    // Step 4(2)
    final int j = biasedExponent(z);
    // These accesses are guaranteed to be within bounds, because:
    // assert (a1.length == NACCUMULATORS);
    // assert (a2.length == NACCUMULATORS);
    // assert (j < NACCUMULATORS);
    // Step 4(3)
    add2.add2(a1[j], z);
    a1[j] = add2.z0;
    // Step 4(4)
    a2[j] += add2.z1;

    // Step 4(5)
    // This addition is guaranteed not to overflow because the
    // next step ascertains that (at this point):
    //assert (i < (NADDS/2)) : i + " < " + NADDS/2;
    // and (for `f32` and `f64`) we have:
    //assert ((NADDS/2) < Integer.MAX_VALUE);
    // thus we can assume:
    //assert ((i+1) <= Integer.MAX_VALUE);
    i += 1;

    // Step 4(6)
    if (i >= NADDS) { compact(); } }

  //--------------------------------------------------------------

  @Override
  public final void addAll (final double[] z,
                            final int start,
                            final int end) {
    assert 0 <= start;
    assert start < end;
    assert end <= z.length;
    for (int ii=start;ii<end;ii++) {
      final double zi = z[ii];
      // Step 4(2)
      final int j = biasedExponent(zi);
      // Step 4(3)
      final double a1j = a1[j];
      a1[j] = a1j + zi;
      final double ej = a1[j] - a1j;
      // Step 4(4)
      a2[j] = (a1j - (a1[j]- ej)) + (a2[j] - ej);
      // Step 4(5)
      this.i++;
      // Step 4(6)
      if (i >= NADDS) { compact(); } } }

  //--------------------------------------------------------------

  public static final double sum (final double[] z) {
    final ZhuHayesOnlineExactNoBranch zh =
      ZhuHayesOnlineExactNoBranch.make();
    zh.addAll(z);
    return zh.doubleValue(); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private ZhuHayesOnlineExactNoBranch () {
    i = 0;
    a1 = new double[NACCUMULATORS];
    a2 = new double[NACCUMULATORS];
    b1v = new double[NACCUMULATORS];
    b2v = new double[NACCUMULATORS];
    ifastinput = new double[2*NACCUMULATORS];
    Arrays.fill(a1,0.0);
    Arrays.fill(a2,0.0);
    Arrays.fill(b1v,0.0);
    Arrays.fill(b2v,0.0);
    Arrays.fill(ifastinput,0.0); }

  public static final ZhuHayesOnlineExactNoBranch make () {
    return new ZhuHayesOnlineExactNoBranch(); }

  //--------------------------------------------------------------
} // end of class
//----------------------------------------------------------------
