package palisades.lakes.elements.java.scripts;

//----------------------------------------------------------------
/** <pre>
 * j --source 11 src/scripts/java/palisades/lakes/elements/java/scripts/JavaNumbers.java > JavaNumbers.txt
 * </pre>
 *
 * @author palisades dot lakes at gmail dot com
 * @version 2018-12-13
 */

@SuppressWarnings("unchecked")
public final class JavaNumbers {

  //--------------------------------------------------------------

  private static final void add (final double a,
                                 final double b) {
    System.out.println(
      String.format("%f + %f = %f",
        Double.valueOf(a),
        Double.valueOf(b),
        Double.valueOf(a+b))); }

  private static final void subtract (final double a,
                                      final double b) {
    System.out.println(
      String.format("%f - %f = %f",
        Double.valueOf(a),
        Double.valueOf(b),
        Double.valueOf(a-b))); }

  private static final void multiply (final double a,
                                      final double b) {
    System.out.println(
      String.format("%f * %f = %f",
        Double.valueOf(a),
        Double.valueOf(b),
        Double.valueOf(a*b))); }

  private static final void divide (final double a,
                                    final double b) {
    System.out.println(
      String.format("%f / %f = %f",
        Double.valueOf(a),
        Double.valueOf(b),
        Double.valueOf(a/b))); }

  public static final void combinations (final double a,
                                         final double b) {
    add(a,b);
    subtract(a,b);
    multiply(a,b);
    divide(a,b); }

  //--------------------------------------------------------------

  public static final void main (final String[] args) {

    final int i0 = (Integer.MAX_VALUE + 2);
    final int i1 = i0 - 3;
    final int i2 = (Integer.MAX_VALUE - 1);
    System.out.println("i0= " + i0);
    System.out.println("i1= " + i1);
    System.out.println("i2= " + i2);
    //    combinations(Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY);
    //    combinations(1.0,Double.POSITIVE_INFINITY);
    //    combinations(0.0,Double.POSITIVE_INFINITY);
    //    combinations(0.0,-0.0);

    //    System.out.println("i =" + Integer.MAX_VALUE); 
    //    System.out.println("i + 1 =" + (Integer.MAX_VALUE + 1)); 

    //    byte b = Byte.MIN_VALUE;
    //    for (int i = 0; i < 3*Byte.MAX_VALUE; i++) {
    //      System.out.println(
    //        String.format("%d : %d %x",
    //          Integer.valueOf(i),
    //          Byte.valueOf(b),
    //          Byte.valueOf(b))); 
    //      b++;}

  }

  //--------------------------------------------------------------
}
//--------------------------------------------------------------
