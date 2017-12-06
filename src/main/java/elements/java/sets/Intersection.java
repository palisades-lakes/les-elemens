package elements.java.sets;

import com.google.common.collect.ImmutableSet;

/** Generic union.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-22
 * @version 2017-06-02
 */

@SuppressWarnings("unchecked")
public final class Intersection extends SetBase {

  private final ImmutableSet _sets;

  //--------------------------------------------------------------

  @Override
  public boolean contains (final boolean x) {
    for (final Object si : _sets) {
      if (! ((Set) si).contains(x)) { return false; } }
    return true; }

  @Override
  public boolean contains (final byte x) {
    for (final Object si : _sets) {
      if (! ((Set) si).contains(x)) { return false; } }
    return true; }

  @Override
  public boolean contains (final char x) {
    for (final Object si : _sets) {
      if (! ((Set) si).contains(x)) { return false; } }
    return true; }

  @Override
  public boolean contains (final double x) {
    for (final Object si : _sets) {
      if (! ((Set) si).contains(x)) { return false; } }
    return true; }

  @Override
  public boolean contains (final float x) {
    for (final Object si : _sets) {
      if (! ((Set) si).contains(x)) { return false; } }
    return true; }

  @Override
  public boolean contains (final int x) {
    for (final Object si : _sets) {
      if (! ((Set) si).contains(x)) { return false; } }
    return true; }

  @Override
  public boolean contains (final long x) {
    for (final Object si : _sets) {
      if (! ((Set) si).contains(x)) { return false; } }
    return true; }

  @Override
  public boolean contains (final short x) {
    for (final Object si : _sets) {
      if (! ((Set) si).contains(x)) { return false; } }
    return true; }

  @Override
  public boolean contains (final Object x) {
    for (final Object si : _sets) {
      if (! ((Set) si).contains(x)) { return false; } }
    return true; }

  @Override
  public Object intersection (final Object set) {

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

  private Intersection (final Iterable sets) {
    _sets = ImmutableSet.copyOf(sets); }

  public static final Intersection make (final Iterable sets) {
    return new Intersection(sets); }

  public static final Intersection make (final Object s0,
                                         final Object s1) {
    return new Intersection(ImmutableSet.of(s0,s1)); }

  ///--------------------------------------------------------------
} // end class
//--------------------------------------------------------------
