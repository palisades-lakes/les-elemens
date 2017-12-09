package palisades.lakes.elements.java.sets;

import java.util.List;

import palisades.lakes.elements.java.sets.CartesianPower;
import palisades.lakes.elements.java.sets.Set;
import palisades.lakes.elements.java.sets.SetBase;

/** Cartesian product of at least 2 terms.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-24
 * @version 2017-06-02
 */

@SuppressWarnings("unchecked")
public final class CartesianPower extends SetBase {

  private final Object _term;
  public final Set term () { return (Set) _term; }
  private final int _nterms;
  public final int nterms () { return _nterms; }

  //--------------------------------------------------------------

  public final boolean contains (final List x)  {
    if (nterms() != x.size()) { return false; }
    for (final Object xi : x) {
      if (! term().contains(xi)) { return false; } }
    return true; }

  public final boolean contains (final boolean[] x)  {
    if (nterms() != x.length) { return false; }
    for (final boolean xi : x) {
      if (! term().contains(xi)) { return false; } }
    return true; }

  public final boolean contains (final byte[] x)  {
    if (nterms() != x.length) { return false; }
    for (final byte xi : x) {
      if (! term().contains(xi)) { return false; } }
    return true; }

  public final boolean contains (final char[] x)  {
    if (nterms() != x.length) { return false; }
    for (final char xi : x) {
      if (! term().contains(xi)) { return false; } }
    return true; }

  public final boolean contains (final double[] x)  {
    if (nterms() != x.length) { return false; }
    for (final double xi : x) {
      if (! term().contains(xi)) { return false; } }
    return true; }

  public final boolean contains (final float[] x)  {
    if (nterms() != x.length) { return false; }
    for (final float xi : x) {
      if (! term().contains(xi)) { return false; } }
    return true; }

  public final boolean contains (final int[] x)  {
    if (nterms() != x.length) { return false; }
    for (final int xi : x) {
      if (! term().contains(xi)) { return false; } }
    return true; }

  public final boolean contains (final long[] x)  {
    if (nterms() != x.length) { return false; }
    for (final long xi : x) {
      if (! term().contains(xi)) { return false; } }
    return true; }

  public final boolean contains (final short[] x)  {
    if (nterms() != x.length) { return false; }
    for (final short xi : x) {
      if (! term().contains(xi)) { return false; } }
    return true; }

  public final boolean contains (final Object[] x)  {
    if (nterms() != x.length) { return false; }
    for (final Object xi : x) {
      if (! term().contains(xi)) { return false; } }
    return true; }

  //--------------------------------------------------------------
  // Set interface
  //--------------------------------------------------------------

  @Override
  public final boolean isEmpty () {
    return ((Set) _term).isEmpty(); }

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
  //  TODO: what if set is mutable?

  private CartesianPower (final Object set,
                          final int nterms) {
    assert (2 <= nterms) : "Power must be at least 2: " + nterms;
    _term = set;
    _nterms = nterms; }

  public static final CartesianPower make (final Object set,
                                           final int nterms) {
    return new CartesianPower(set,nterms); }

  //--------------------------------------------------------------
} // end class
//--------------------------------------------------------------
