package palisades.lakes.elements.java.sets;

import com.google.common.collect.ImmutableSet;

import palisades.lakes.elements.java.sets.Set;
import palisades.lakes.elements.java.sets.SetBase;
import palisades.lakes.elements.java.sets.Union;

/** Generic union.
 *
 * @author palisades dot lakes at gmail dot com
 * @since 2017-05-22
 * @version 2017-06-02
 */

@SuppressWarnings("unchecked")
public final class Union extends SetBase {

  private final ImmutableSet _sets;

  //--------------------------------------------------------------

  @Override
  public final boolean isEmpty () {
    for (final Object s : _sets) {
      if (! ((Set) s).isEmpty()) { return false; } }
    return true; }

  @Override
  public final boolean contains (final boolean x) {
    for (final Object si : _sets) {
      if (((Set) si).contains(x)) { return true; } }
    return false; }

  @Override
  public final boolean contains (final byte x) {
    for (final Object si : _sets) {
      if (((Set) si).contains(x)) { return true; } }
    return false; }

  @Override
  public final boolean contains (final char x) {
    for (final Object si : _sets) {
      if (((Set) si).contains(x)) { return true; } }
    return false; }

  @Override
  public final boolean contains (final double x) {
    for (final Object si : _sets) {
      if (((Set) si).contains(x)) { return true; } }
    return false; }

  @Override
  public final boolean contains (final float x) {
    for (final Object si : _sets) {
      if (((Set) si).contains(x)) { return true; } }
    return false; }

  @Override
  public final boolean contains (final int x) {
    for (final Object si : _sets) {
      if (((Set) si).contains(x)) { return true; } }
    return false; }

  @Override
  public final boolean contains (final long x) {
    for (final Object si : _sets) {
      if (((Set) si).contains(x)) { return true; } }
    return false; }

  @Override
  public final boolean contains (final short x) {
    for (final Object si : _sets) {
      if (((Set) si).contains(x)) { return true; } }
    return false; }

  @Override
  public final boolean contains (final Object x) {
    for (final Object si : _sets) {
      if (((Set) si).contains(x)) { return true; } }
    return false; }

  @Override
  public final Object union (final Object set) {

    if (set instanceof Set) {
      final ImmutableSet.Builder b = ImmutableSet.builder();
      b.addAll(_sets);
      b.add(set);
      return make(b.build()); }

    throw new UnsupportedOperationException(
      "union" + " unsupported for " + getClass()
      + " and " + set.getClass()); }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private Union (final Iterable sets) {
    _sets = ImmutableSet.copyOf(sets); }

  public static final Union make (final Iterable sets) {
    return new Union(sets); }

  public static final Union make (final Object s0,
                                  final Object s1) {
    return new Union(ImmutableSet.of(s0,s1)); }

  //--------------------------------------------------------------
} // end class
//--------------------------------------------------------------
