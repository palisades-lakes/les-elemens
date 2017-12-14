package palisades.lakes.elements.java.numbers;

import java.util.Set;

import palisades.lakes.elements.java.numbers.IntegerInterval;
import palisades.lakes.elements.java.sets.SetBase;

//----------------------------------------------------------------
/** Half-open [min,max) interval in expressed in
 * <code>int</code>, but applicable to any primitive or Object
 * number.
 *
 * TODO: how to implement [x,infinity]?
 * TODO: empty interval different from general empty set? has a 
 * location so it can be transformed by functions R-&gt;R?
 * TODO: any sense in [i1,i0) where i1 > i0 ? Complement?
 *
 * @author palisades dot lakes at gmail dot com
 * @since 2017-05-29
 * @version 2017-05-29
 */

public final class IntegerInterval extends SetBase {

  public final int min;
  public final int max;

  //--------------------------------------------------------------
  // methods
  //--------------------------------------------------------------

  //--------------------------------------------------------------
  // Set interface
  //--------------------------------------------------------------

  @Override
  public final boolean contains (final boolean x) { return false; }

  @Override
  public final boolean contains (final byte x) {
    return (min <= x) && (x < max); }

  @Override
  public final boolean contains (final char x) { return false; }

  @Override
  public final boolean contains (final double x) {
    return (min <= x) && (x < max); }

  @Override
  public final boolean contains (final float x) {
    return (min <= x) && (x < max); }

  @Override
  public final boolean contains (final int x) {
    return (min <= x) && (x < max); }

  @Override
  public final boolean contains (final long x) {
    return (min <= x) && (x < max); }

  @Override
  public final boolean contains (final short x) {
    return (min <= x) && (x < max); }

  @Override
  public final boolean contains (final Object x) {
    if (x instanceof Number) {
      final double z = ((Number) x).doubleValue();
      return (min <= z) && (z < max); }
    return false; }

  public final boolean intersects (final IntegerInterval that) {
    if (max <= that.min) { return false; }
    if (that.max <= min) { return false; }
    return true; }

  public final boolean intersects (final Set that) {
    for (final Object x : that) {
      if (contains(x)) { return true; } }
    return false; }

  @Override
  public final boolean intersects (final Object set) {
    if (set instanceof IntegerInterval) {
      return intersects((IntegerInterval) set); }
    if (set instanceof Set) {
      return intersects((Set) set); }
    throw new UnsupportedOperationException(
      "intersects" + " unsupported for " + getClass()); }

   public final boolean strictSubsetOf (final IntegerInterval i) {
    if (min <= i.min) { return false; }
    if (max >= i.max) { return false; }
    return true; }

  @Override
  public final boolean strictSubsetOf (final Object set) {
    if (set instanceof IntegerInterval) {
      return strictSubsetOf((IntegerInterval) set); }
    throw new UnsupportedOperationException(
      "strictSubsetOf" + " unsupported for " + getClass()); }

  public final boolean subsetOf (final IntegerInterval that) {
    if (min < that.min) { return false; }
    if (max > that.max) { return false; }
    return true; }

  @Override
  public final boolean subsetOf (final Object set) {
    if (set instanceof IntegerInterval) {
      return subsetOf((IntegerInterval) set); }
    throw new UnsupportedOperationException(
      "subsetOf" + " unsupported for " + getClass()); }

  //--------------------------------------------------------------
  // Set interface
  //--------------------------------------------------------------

  private IntegerInterval (final int i0, 
                           final int i1) {
    assert (i0 <= i1);
    min = i0; max = i1; }

  public static final IntegerInterval make (final int i0,
                                            final int i1) {

    if (i0 <= i1) { return new IntegerInterval(i0,i1); }
    return new IntegerInterval(i1,i0); }

  //--------------------------------------------------------------
} // end of class
//----------------------------------------------------------------
