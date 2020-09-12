package org.wj.bbdy;

/**
 * 重新定义类的field
 */
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.pool.TypePool;

public class MyApplication {
  public static void main(String[] args) {
    TypePool typePool = TypePool.Default.ofClassPath();
    new ByteBuddy().redefine(typePool.describe("org.foo.Bar").resolve(), // do not use 'Bar.class'
        ClassFileLocator.ForClassLoader.ofClassPath()).defineField("qux", String.class) // we learn more about defining
                                                                                        // fields later
        .make().load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);
    try {
      System.out.println(org.foo.Bar.class.getDeclaredField("qux").toString());
    } catch (NoSuchFieldException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
