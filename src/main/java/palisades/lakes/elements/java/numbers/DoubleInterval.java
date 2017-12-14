package palisades.lakes.elements.java.numbers;

import java.util.Set;

import palisades.lakes.elements.java.numbers.DoubleInterval;
import palisades.lakes.elements.java.sets.SetBase;

//----------------------------------------------------------------
/** Half-open [min,max) interval in expressed in
 * <code>double</code>, but applicable to any primitive or Object
 * number.
 *
 * TODO: how to implement [x,infinity]?
 * TODO: empty interval different from general empty set? has a 
 * location so it can be transformed by functions R-&gt;R?
 * TODO: any sense in [z1,z0) where z1 > z0 ?
 *
 * @author palisades dot lakes at gmail dot com
 * @since 2017-05-22
 * @version 2017-05-29
 */

public final class DoubleInterval extends SetBase {

  public final double min;
  public final double max;
  
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

  public final boolean intersects (final DoubleInterval that) {
    if (max <= that.min) { return false; }
    if (that.max <= min) { return false; }
    return true; }

  public final boolean intersects (final Set that) {
    for (final Object x : that) {
      if (contains(x)) { return true; } }
    return false; }

  @Override
  public final boolean intersects (final Object set) {
    if (set instanceof DoubleInterval) {
      return intersects((DoubleInterval) set); }
    if (set instanceof Set) {
      return intersects((Set) set); }
    throw new UnsupportedOperationException(
      "intersects" + " unsupported for " + getClass()); }

  public final boolean strictSubsetOf (final DoubleInterval i) {
    if (min <= i.min) { return false; }
    if (max >= i.max) { return false; }
    return true; }

  @Override
  public final boolean strictSubsetOf (final Object set) {
    if (set instanceof DoubleInterval) {
      return strictSubsetOf((DoubleInterval) set); }
    throw new UnsupportedOperationException(
      "strictSubsetOf" + " unsupported for " + getClass()); }

  public final boolean subsetOf (final DoubleInterval that) {
    if (min < that.min) { return false; }
    if (max > that.max) { return false; }
    return true; }

  @Override
  public final boolean subsetOf (final Object set) {
    if (set instanceof DoubleInterval) {
      return subsetOf((DoubleInterval) set); }
    throw new UnsupportedOperationException(
      "subsetOf" + " unsupported for " + getClass()); }

  //--------------------------------------------------------------
  // Set interface
  //--------------------------------------------------------------

  private DoubleInterval (final double z0, 
                          final double z1) {
    assert (z0 <= z1);
    min = z0; max = z1; }

  public static final DoubleInterval make (final double z0,
                                           final double z1) {
    
    assert ! Double.isNaN(z0);
    assert ! Double.isNaN(z1);

    if (z0 <= z1) { return new DoubleInterval(z0,z1); }
    return new DoubleInterval(z1,z0); }

  //--------------------------------------------------------------
} // end of class
//----------------------------------------------------------------
