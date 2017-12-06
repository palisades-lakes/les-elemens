package elements.java.generic;

/** A pair of classes, for optimizing multimethod dispatch 
 * functions.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-06-05
 * @version 2017-06-05
 */

@SuppressWarnings("unchecked")
public final class SignatureN implements Signature {

  public final Class[] classes;

  //--------------------------------------------------------------
  
  public final boolean isAssignableFrom (final SignatureN that) {
    final Class[] those = that.classes;
    for (int i=0;i<classes.length;i++) {
      if (! classes[i].isAssignableFrom(those[i])) {
        return false; } }
    return true; }
  
  //--------------------------------------------------------------
  
  @Override
  public final boolean isAssignableFrom (final Signature that) {
    if (that instanceof SignatureN) {
      return isAssignableFrom((SignatureN) that); }
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
    return false; }

  @Override
  public final boolean isAssignableFrom (final Class... ks) {
    for (int i=0;i<classes.length;i++) {
      if (! classes[i].isAssignableFrom(ks[i])) {
        return false; } }
    return true; }

  //--------------------------------------------------------------

  @Override
  public final boolean equiv (final Class k0,
                               final Class k1) {
    return false; }
  
  @Override
  public final boolean equiv (final Class k0, 
                               final Class k1, 
                               final Class k2) {
    return false; }
  
  @Override
  public final boolean equiv (final Class... ks) {
    for (int i=0;i<classes.length;i++) {
      if (! classes[i].equals(ks[i])) { return false; } }
    return true; }
  
  //--------------------------------------------------------------
  // Object interface
  //--------------------------------------------------------------

  @Override
  public final int hashCode () {
    int result = 17;
    for (final Class c : classes) {
      result = 37*result + c.hashCode(); }
    return result; }

  @Override
  public final boolean equals (final Object that) {
    if (this == that) { return true; }
    if (that instanceof SignatureN) {
      return equiv(((SignatureN) that).classes); }
    return false; }
  
  @Override
  public final String toString () {
    final StringBuilder builder = 
      new StringBuilder("(");
    builder.append(getClass().getSimpleName());
    builder.append(". "); 
    for (final Class c : classes) {
      builder.append(c.getName());
      builder.append(" "); } 
    builder.append(")"); 
    return builder.toString(); }
  
  //--------------------------------------------------------------

  private SignatureN (final Class... ks) {
    classes = ks; }

  public static final SignatureN get (final Class... ks) {
    return new SignatureN(ks); }

  // TODO: memoize singleton instances?
  
  //--------------------------------------------------------------
}
//--------------------------------------------------------------
