package palisades.lakes.elements.java.sets;

import palisades.lakes.elements.java.sets.Difference;
import palisades.lakes.elements.java.sets.Set;
import palisades.lakes.elements.java.sets.SetBase;

/** Elements of s0 not in s1.
 *
 * @author palisades dot lakes at gmail dot com
 * @since 2017-05-22
 * @version 2017-06-02
 */
public final class Difference extends SetBase {

  private final Set _s0;
  private final Set _s1;

  //--------------------------------------------------------------

  @Override
  public final boolean contains (final boolean x) {
    return _s0.contains(x) && (! _s1.contains(x)); }

  @Override
  public final boolean contains (final byte x) {
    return _s0.contains(x) && (! _s1.contains(x)); }

  @Override
  public final boolean contains (final char x) {
    return _s0.contains(x) && (! _s1.contains(x)); }

  @Override
  public final boolean contains (final double x) {
    return _s0.contains(x) && (! _s1.contains(x)); }

  @Override
  public final boolean contains (final float x) {
    return _s0.contains(x) && (! _s1.contains(x)); }

  @Override
  public final boolean contains (final int x) {
    return _s0.contains(x) && (! _s1.contains(x)); }

  @Override
  public final boolean contains (final long x) {
    return _s0.contains(x) && (! _s1.contains(x)); }

  @Override
  public final boolean contains (final short x) {
    return _s0.contains(x) && (! _s1.contains(x)); }

  @Override
  public final boolean contains (final Object x) {
    return _s0.contains(x) && (! _s1.contains(x)); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private Difference (final Set s0, final Set s1) {
    _s0 = s0; _s1 = s1; }

  public static final Difference make (final Object s0,
                                       final Object s1) {
    return new Difference((Set) s0, (Set) s1); }

  ///--------------------------------------------------------------
} // end class
//--------------------------------------------------------------
