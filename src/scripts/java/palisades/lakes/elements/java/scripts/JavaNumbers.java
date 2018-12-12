package palisades.lakes.elements.java.scripts;

//----------------------------------------------------------------
/** <pre>
 * j --source 11 src/scripts/java/palisades/lakes/elements/java/scripts/JavaNumbers.java > JavaNumbers.txt
 * </pre>
 *
 * @author palisades dot lakes at gmail dot com
 * @version 2018-12-12
 */

@SuppressWarnings("unchecked")
public final class JavaNumbers {

  //--------------------------------------------------------------

  public static final void main (final String[] args) {

//    System.out.println("i =" + Integer.MAX_VALUE); 
//    System.out.println("i + 1 =" + (Integer.MAX_VALUE + 1)); 
    
    byte b = Byte.MIN_VALUE;
    for (int i = 0; i < 3*Byte.MAX_VALUE; i++) {
      System.out.println(
        String.format("%d : %d %x",
          Integer.valueOf(i),
          Byte.valueOf(b),
          Byte.valueOf(b))); 
      b++;}
  }

  //--------------------------------------------------------------
}
//--------------------------------------------------------------
