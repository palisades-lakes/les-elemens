package palisades.lakes.elements.java.accumulate;

import palisades.lakes.elements.java.accumulate.Accumulator;

/** Base class for accumulators. Most methods throw an
 * {@link UnsupportedOperationException}.
 * <p>
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-16
 * @version 2017-06-02
 */
public abstract class AccumulatorBase implements Accumulator {

  //--------------------------------------------------------------
  // Objects
  //--------------------------------------------------------------

  @Override
  public Object value () {
    throw new UnsupportedOperationException(
      "value" + " unsupported for " + getClass()); }

  @Override
  public void add (final Object z) {
    throw new UnsupportedOperationException(
      "add" + " unsupported for " + getClass()); }

  @Override
  public void remove (final Object z) {
    throw new UnsupportedOperationException(
      "remove" + " unsupported for " + getClass()); }

  @Override
  public void addAll (final Object z,
                      final int start,
                      final int end) {
    throw new UnsupportedOperationException(
      "addAll" + " unsupported for " + getClass()); }

  @Override
  public void removeAll (final Object z,
                         final int start,
                         final int end) {
    throw new UnsupportedOperationException(
      "removeAll" + " unsupported for " + getClass()); }

  @Override
  public void addAll (final Object z) {
    if (z instanceof Iterable) {
      for (final Object zi : (Iterable) z) { add(zi); } }
    else {
      throw new UnsupportedOperationException(
        "addAll" + " unsupported for " + getClass()); } }

  @Override
  public void removeAll (final Object z) {
    if (z instanceof Iterable) {
      for (final Object zi : (Iterable) z) { remove(zi); } }
    else {
      throw new UnsupportedOperationException(
        "removeAll" + " unsupported for " + getClass()); } }

  //--------------------------------------------------------------
  // primitives
  //--------------------------------------------------------------

  @Override
  public double doubleValue () {
    throw new UnsupportedOperationException(
      "doubleValue" + " unsupported for " + getClass()); }

  @Override
  public void add (final double z) {
    throw new UnsupportedOperationException(
      "add" + " unsupported for " + getClass()); }

  @Override
  public void remove (final double z) {
    throw new UnsupportedOperationException(
      "remove" + " unsupported for " + getClass()); }

  @Override
  public void addAll (final double[] z,
                      final int start,
                      final int end) {
    assert (0 <= start);
    assert (start < end);
    assert (end <= z.length);
    for (int i=start;i<end;i++) { add(z[i]); } }

  @Override
  public void removeAll (final double[] z,
                         final int start,
                         final int end) {
    assert (0 <= start);
    assert (start < end);
    assert (end <= z.length);
    for (int i=start;i<end;i++) { remove(z[i]); } }

  @Override
  public void addAll (final double[] z) {
    addAll(z,0,z.length); }

  @Override
  public void removeAll (final double[] z) {
    removeAll(z,0,z.length); }

  @Override
  public void add (final double z0, final double z1) {
    throw new UnsupportedOperationException(
      "add" + " unsupported for " + getClass()); }

  // @see mthcmp.java.accumulate.Accumulator#addAll(double[], double[], int, int)

  @Override
  public void addAll (final double[] z0,
                      final double[] z1,
                      final int start,
                      final int end) {
    assert (0 <= start);
    assert (start < end);
    assert (end <= z0.length);
    assert (end <= z1.length);
    for (int i=start;i<end;i++) { add(z0[i],z1[i]); } }

  @Override
  public void addAll (final double[] z0, final double[] z1) {
    assert z0.length == z1.length;
    addAll(z0,z1,0,z0.length); }

  //--------------------------------------------------------------
  // disable Object methods broken by mutability
  //--------------------------------------------------------------

  @Override
  public final boolean equals (final Object obj) {
    throw new UnsupportedOperationException(
      "equals" + " unsupported for " + getClass()); }

  @Override
  public final int hashCode () {
    throw new UnsupportedOperationException(
      "hashCode" + " unsupported for " + getClass()); }


}
