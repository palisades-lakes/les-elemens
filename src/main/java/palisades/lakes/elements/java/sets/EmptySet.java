package palisades.lakes.elements.java.sets;

import palisades.lakes.elements.java.sets.EmptySet;
import palisades.lakes.elements.java.sets.Set;
import palisades.lakes.elements.java.sets.SetBase;

/**
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-22
 * @version 2017-06-02
 */

public final class EmptySet extends SetBase {

  @Override
  public final boolean isEmpty () { return true; }
  @Override
  public final Object cardinality () { return Integer.valueOf(0); }
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
  public final boolean contains (final Object x) { return false; }
  @Override
  public final boolean intersects (final Object set) { return false; }
  @Override
  public final boolean strictSubsetOf (final Object set) {
    return ! ((Set) set).isEmpty(); }
  @Override
  public final boolean subsetOf (final Object set) { return false; }
  @Override
  public final Object intersection (final Object set) { return this; }
  @Override
  public final Object union (final Object set) { return set; }
  @Override
  public final Object difference (final Object set) { return this; }

  private EmptySet () { }
  private static final EmptySet SINGLETON = new EmptySet();
  public static final EmptySet get () { return SINGLETON; } }
