package elements.java.sets;

public final class MyClassLoader extends java.lang.ClassLoader {
  public final Class defineClass (final String name, 
                                  final byte[] b) {
    return super.defineClass(name, b, 0, b.length); } }
