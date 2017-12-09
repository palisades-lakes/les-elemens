package palisades.lakes.elements.java.generic;

import palisades.lakes.elements.java.generic.Signature;
import palisades.lakes.elements.java.generic.Signature3;

/** A pair of classes, for optimizing multimethod dispatch 
 * functions.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-06-05
 * @version 2017-06-06
 */

@SuppressWarnings("unchecked")
public final class Signature3 implements Signature {

  public final Class c0;
  public final Class c1;
  public final Class c2;

  //--------------------------------------------------------------
  
  public final boolean isAssignableFrom (final Signature3 that) {
    return
      c0.isAssignableFrom(that.c0)
      &&
      c1.isAssignableFrom(that.c1)
      &&
      c2.isAssignableFrom(that.c2); }
  
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
      c0.isAssignableFrom(k0) && 
      c1.isAssignableFrom(k1) && 
      c2.isAssignableFrom(k2); }

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
      c0.equals(k0) && 
      c1.equals(k1) && 
      c2.equals(k2); }
  
  @Override
  public final boolean equiv (final Class... ks) { 
    return false; }
  
  //--------------------------------------------------------------
  // Object interface
  //--------------------------------------------------------------

  @Override
  public final int hashCode () {
    return  
      37*(37*((37*17) + c0.hashCode()) 
        + c1.hashCode())
      + c2.hashCode(); }

  @Override
  public final boolean equals (final Object that) {
    if (this == that) { return true; }
    if (that instanceof Signature3) {
      return 
        c0.equals(((Signature3) that).c0) 
        &&
        c1.equals(((Signature3) that).c1) 
        &&
        c2.equals(((Signature3) that).c2); }
    return false; }
  
  @Override
  public final String toString () {
    return "(" + getClass().getSimpleName() + ". " 
      + c0.getName() + " " 
      + c1.getName() + " " 
      + c2.getName() + ")"; }
  
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
