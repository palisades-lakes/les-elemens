package elements.java.generic;

/** A pair of classes, for optimizing multimethod dispatch 
 * functions.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-06-02
 * @version 2017-06-06
 */

@SuppressWarnings("unchecked")
public final class Signature2 implements Signature {

  public final Class c0;
  public final Class c1;

  //--------------------------------------------------------------
  
  public final boolean isAssignableFrom (final Signature2 that) {
    return
    c0.isAssignableFrom(that.c0)
    &&
    c1.isAssignableFrom(that.c1); }
  
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
    return c0.isAssignableFrom(k0) && c1.isAssignableFrom(k1); }

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
    return c0.equals(k0) && c1.equals(k1); }
  
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
    return  37*((37*17) + c0.hashCode()) + c1.hashCode(); }

  @Override
  public final boolean equals (final Object that) {
    if (this == that) { return true; }
    if (that instanceof Signature2) {
      return 
        c0.equals(((Signature2) that).c0) 
        &&
        c1.equals(((Signature2) that).c1); }
    return false; }
  
  @Override
  public final String toString () {
    return "(" + getClass().getSimpleName() + ". " 
      + c0.getName() + " " + c1.getName() + ")"; }
  
  //--------------------------------------------------------------

  private Signature2 (final Class k0, final Class k1) {
    c0 = k0; c1 = k1; }

  public static final Signature2 get (final Class k0, final Class k1) {
    return new Signature2(k0,k1); }


  // TODO: memoize singleton instances?
  
  //--------------------------------------------------------------
}
//--------------------------------------------------------------
