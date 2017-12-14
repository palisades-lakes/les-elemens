package palisades.lakes.elements.java.generic;

import java.util.Objects;

import palisades.lakes.elements.java.generic.Signature;
import palisades.lakes.elements.java.generic.Signature3;

/** A pair of classes, for optimizing multimethod dispatch 
 * functions.
 *
 * @author palisades dot lakes at gmail dot com
 * @version 2017-12-13
 */

@SuppressWarnings("unchecked")
public final class Signature3 implements Signature {

  public final Class c0;
  public final Class c1;
  public final Class c2;

  //--------------------------------------------------------------

  public final boolean isAssignableFrom (final Signature3 that) {
    return
      Classes.isAssignableFrom(c0,that.c0)
      &&
      Classes.isAssignableFrom(c1,that.c1)
      &&
      Classes.isAssignableFrom(c2,that.c2); }

  //--------------------------------------------------------------

  @Override
  public final boolean isAssignableFrom (final Signature that) {
    if (that instanceof Signature3) {
      return isAssignableFrom((Signature3) that); }
    return false; }

  @Override
  public final boolean isAssignableFrom (Class k) { 
    return false; }

  @Override
  public final boolean isAssignableFrom (final Class k0, 
                                         final Class k1) {
    return false; }

  @Override
  public final boolean isAssignableFrom (final Class k0, 
                                         final Class k1,
                                         final Class k2) {
    return 
      Classes.isAssignableFrom(c0,k0) && 
      Classes.isAssignableFrom(c1,k1) && 
      Classes.isAssignableFrom(c2,k2); }

  @Override
  public final boolean isAssignableFrom (final Class... ks) {
    return false; }

  //--------------------------------------------------------------

  @Override
  public final boolean equiv (final Class k0,
                              final Class k1) {
    return false; }

  @Override
  public final boolean equiv (final Class k0, 
                              final Class k1, 
                              final Class k2) {
    return 
      Objects.equals(c0,k0) && 
      Objects.equals(c1,k1) && 
      Objects.equals(c2,k2); }

  @Override
  public final boolean equiv (final Class... ks) { 
    return false; }

  //--------------------------------------------------------------
  // Object interface
  //--------------------------------------------------------------

  @Override
  public final int hashCode () {
    return  
      37*(37*((37*17) + Objects.hashCode(c0)) 
        + Objects.hashCode(c1))
      + Objects.hashCode(c2); }

  @Override
  public final boolean equals (final Object that) {
    if (this == that) { return true; }
    if (that instanceof Signature3) {
      return 
        Objects.equals(c0,((Signature3) that).c0) 
        &&
        Objects.equals(c1,((Signature3) that).c1) 
        &&
        Objects.equals(c2,((Signature3) that).c2); }
    return false; }

  @Override
  public final String toString () {
    return "(" + getClass().getSimpleName() + ". " 
      + Classes.getName(c0) + " " 
      + Classes.getName(c1) + " " 
      + Classes.getName(c2) + ")"; }

  //--------------------------------------------------------------

  private Signature3 (final Class k0, 
                      final Class k1, 
                      final Class k2) {
    c0 = k0; c1 = k1; c2 = k2; }

  public static final Signature3 get (final Class k0, 
                                      final Class k1, 
                                      final Class k2) {
    return new Signature3(k0,k1,k2); }


  // TODO: memoize singleton instances?

  //--------------------------------------------------------------
}
//--------------------------------------------------------------
