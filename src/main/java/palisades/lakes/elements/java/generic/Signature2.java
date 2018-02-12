package palisades.lakes.elements.java.generic;

import java.util.Objects;

import palisades.lakes.elements.java.generic.Signature;
import palisades.lakes.elements.java.generic.Signature2;

/** A pair of classes, for optimizing multimethod dispatch 
 * functions.
 *
 * @author palisades dot lakes at gmail dot com
 * @version 2018-02-12
 */

@SuppressWarnings("unchecked")
public final class Signature2 implements Signature {

  public final Class c0;
  public final Class c1;

  //--------------------------------------------------------------

  public final boolean isAssignableFrom (final Signature2 that) {
    return
      Classes.isAssignableFrom(c0,that.c0)
      &&
      Classes.isAssignableFrom(c1,that.c1); }

  //--------------------------------------------------------------

  @Override
  public final boolean isAssignableFrom (final Signature that) {
    if (that instanceof Signature2) {
      return isAssignableFrom((Signature2) that); }
    return false; }

  @Override
  public final boolean isAssignableFrom (Class k) { 
    return false; }

  @Override
  public final boolean isAssignableFrom (final Class k0, 
                                         final Class k1) {
    return Classes.isAssignableFrom(c0,k0) && 
      Classes.isAssignableFrom(c1,k1); }

  @Override
  public final boolean isAssignableFrom (final Class k0, 
                                         final Class k1,
                                         final Class k2) {
    return false; }

  @Override
  public final boolean isAssignableFrom (final Class... ks) {
    return false; }

  //--------------------------------------------------------------

  @Override
  public final boolean equiv (final Class k0,
                              final Class k1) {
    return Objects.equals(c0,k0) && Objects.equals(c1,k1); }

  @Override
  public final boolean equiv (final Class k0, 
                              final Class k1, 
                              final Class k2) {
    return false; }

  @Override
  public final boolean equiv (final Class... ks) {
    return false; }

  //--------------------------------------------------------------
  // Object interface
  //--------------------------------------------------------------

  @Override
  public final int hashCode () {
    return  31*((31*17) + Objects.hashCode(c0)) 
      + Objects.hashCode(c1); }

  @Override
  public final boolean equals (final Object that) {
    if (this == that) { return true; }
    if (that instanceof Signature2) {
      return 
        Objects.equals(c0,((Signature2) that).c0) 
        &&
        Objects.equals(c1,((Signature2) that).c1); }
    return false; }

  @Override
  public final String toString () {
    return "(" + getClass().getSimpleName() + ". " 
      + Classes.getName(c0) + " " + Classes.getName(c1) + ")"; }

  //--------------------------------------------------------------

  private Signature2 (final Class k0, final Class k1) {
    c0 = k0; c1 = k1; }

  public static final Signature2 get (final Class k0, final Class k1) {
    return new Signature2(k0,k1); }


  // TODO: memoize singleton instances?

  //--------------------------------------------------------------
}
//--------------------------------------------------------------
