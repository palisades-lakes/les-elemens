package elements.java.sets;

/**
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-22
 * @version 2017-06-02
 */
public abstract class SetBase implements Set {

  @Override
  public boolean isEmpty () {
    throw new UnsupportedOperationException(
      "isEmpty" + " unsupported for " + getClass()); }

  @Override
  public Object cardinality () {
    throw new UnsupportedOperationException(
      "cardinality" + " unsupported for " + getClass()); }

  @Override
  public boolean contains (final boolean x) {
    throw new UnsupportedOperationException(
      "containsElement" + " unsupported for " + getClass()
      + " and " + x); }

  @Override
  public boolean contains (final byte x) {
    throw new UnsupportedOperationException(
      "containsElement" + " unsupported for " + getClass()
      + " and " + x); }

  @Override
  public boolean contains (final char x) {
    throw new UnsupportedOperationException(
      "containsElement" + " unsupported for " + getClass()
      + " and " + x); }

  @Override
  public boolean contains (final double x) {
    throw new UnsupportedOperationException(
      "containsElement" + " unsupported for " + getClass()
      + " and " + x); }

  @Override
  public boolean contains (final float x) {
    throw new UnsupportedOperationException(
      "containsElement" + " unsupported for " + getClass()
      + " and " + x); }

  @Override
  public boolean contains (final int x) {
    throw new UnsupportedOperationException(
      "containsElement" + " unsupported for " + getClass()
      + " and " + x); }

  @Override
  public boolean contains (final long x) {
    throw new UnsupportedOperationException(
      "containsElement" + " unsupported for " + getClass()
      + " and " + x); }

  @Override
  public boolean contains (final short x) {
    throw new UnsupportedOperationException(
      "containsElement" + " unsupported for " + getClass()
      + " and " + x); }

  @Override
  public boolean contains (final Object x) {
    throw new UnsupportedOperationException(
      "containsElement" + " unsupported for " + getClass()
      + " and " + x.getClass()); }

  @Override
  public boolean intersects (final Object set) {
    throw new UnsupportedOperationException(
      "intersects" + " unsupported for " + getClass()); }

  @Override
  public boolean strictSubsetOf (final Object set) {
    throw new UnsupportedOperationException(
      "strictSubsetOf" + " unsupported for " + getClass()
      + " and " + set.getClass()); }

  @Override
  public boolean subsetOf (final Object set) {
    throw new UnsupportedOperationException(
      "subsetOf" + " unsupported for " + getClass()
      + " and " + set.getClass()); }

  @Override
  public Object intersection (final Object set) {
    throw new UnsupportedOperationException(
      "intersection" + " unsupported for " + getClass()); }

  @Override
  public Object union (final Object set) {
    throw new UnsupportedOperationException(
      "union" + " unsupported for " + getClass()
      + " and " + set.getClass()); }

  @Override
  public Object difference (final Object set) {
    throw new UnsupportedOperationException(
      "difference" + " unsupported for " + getClass()
      + " and " + set.getClass()); }

}
