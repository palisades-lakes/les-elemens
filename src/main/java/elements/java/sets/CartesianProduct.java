package elements.java.sets;

import java.util.List;

import com.google.common.collect.ImmutableList;

/** Cartesian product of at least 2 terms.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-24
 * @version 2017-06-02
 */

@SuppressWarnings("unchecked")
public final class CartesianProduct extends SetBase {

  private final ImmutableList _terms;
  public final List terms () { return _terms; }

  public final Set term (final int i) {
    return (Set) _terms.get(i); }

  //--------------------------------------------------------------

  public final int nterms () { return _terms.size(); }

  public final boolean contains (final List x)  {
    if (nterms() != x.size()) { return false; }
    for (int i=0;i<nterms();i++) {
      if (! term(i).contains(x.get(i))) { return false; } }
    return true; }

  public final boolean contains (final boolean[] x)  {
    if (nterms() != x.length) { return false; }
    for (int i=0;i<nterms();i++) {
      if (! term(i).contains(x[i])) { return false; } }
    return true; }

  public final boolean contains (final byte[] x)  {
    if (nterms() != x.length) { return false; }
    for (int i=0;i<nterms();i++) {
      if (! term(i).contains(x[i])) { return false; } }
    return true; }

  public final boolean contains (final char[] x)  {
    if (nterms() != x.length) { return false; }
    for (int i=0;i<nterms();i++) {
      if (! term(i).contains(x[i])) { return false; } }
    return true; }

  public final boolean contains (final double[] x)  {
    if (nterms() != x.length) { return false; }
    for (int i=0;i<nterms();i++) {
      if (! term(i).contains(x[i])) { return false; } }
    return true; }

  public final boolean contains (final float[] x)  {
    if (nterms() != x.length) { return false; }
    for (int i=0;i<nterms();i++) {
      if (! term(i).contains(x[i])) { return false; } }
    return true; }

  public final boolean contains (final int[] x)  {
    if (nterms() != x.length) { return false; }
    for (int i=0;i<nterms();i++) {
      if (! term(i).contains(x[i])) { return false; } }
    return true; }

  public final boolean contains (final long[] x)  {
    if (nterms() != x.length) { return false; }
    for (int i=0;i<nterms();i++) {
      if (! term(i).contains(x[i])) { return false; } }
    return true; }

  public final boolean contains (final short[] x)  {
    if (nterms() != x.length) { return false; }
    for (int i=0;i<nterms();i++) {
      if (! term(i).contains(x[i])) { return false; } }
    return true; }

  public final boolean contains (final Object[] x)  {
    if (nterms() != x.length) { return false; }
    for (int i=0;i<nterms();i++) {
      if (! term(i).contains(x[i])) { return false; } }
    return true; }

  //--------------------------------------------------------------
  // Set interface
  //--------------------------------------------------------------

  @Override
  public final boolean isEmpty () {
    for (final Object s : _terms) {
      if (((Set) s).isEmpty()) { return true; } }
    return false; }

  @Override
  public final boolean contains (final boolean x) { return false; }
  @Override
  public final boolean contains (final byte x) { return false; }
  @Override
  public final boolean contains (final char x) { return false; }
  @Override
  public final boolean contains (final double x) { return false; }
  @Override
  public final boolean contains (final float x) { return false; }
  @Override
  public final boolean contains (final int x) { return false; }
  @Override
  public final boolean contains (final long x) { return false; }
  @Override
  public final boolean contains (final short x) { return false; }

  @Override
  public final boolean contains (final Object x) {

    if (x instanceof List) { return contains((List) x); }

    if (x instanceof boolean[]) { return contains((boolean[]) x); }
    if (x instanceof byte[]) { return contains((byte[]) x); }
    if (x instanceof char[]) { return contains((char[]) x); }
    if (x instanceof double[]) { return contains((double[]) x); }
    if (x instanceof float[]) { return contains((float[]) x); }
    if (x instanceof int[]) { return contains((int[]) x); }
    if (x instanceof long[]) { return contains((long[]) x); }
    if (x instanceof short[]) { return contains((short[]) x); }
    if (x instanceof Object[]) { return contains((Object[]) x); }

    throw new UnsupportedOperationException(
      "Don't know how to treat " + x.getClass().getSimpleName()
      + " as a tuple of " + this); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------
  //  TODO: what if some of the sets are mutable?

  private CartesianProduct (final List sets) {
    assert (2 <= sets.size()) :
      "Must have a least 2 terms for a CartesianProduct: "
      + sets.size();
    _terms = ImmutableList.copyOf(sets); }

  public static final CartesianProduct make (final List sets) {
    return new CartesianProduct(sets); }

  public static final CartesianProduct make (final Object s0,
                                             final Object s1) {
    return new CartesianProduct(ImmutableList.of(s0,s1)); }

  //--------------------------------------------------------------
} // end class
//--------------------------------------------------------------
