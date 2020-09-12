package org.wj.bbdy;

/**
 * 通过一个类重置一个新的类，并将不改变原来的类type,并将调用重置后类的方法
 */
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

public class ReloadClass {

  public static void main(String[] args) {
    ByteBuddyAgent.installOnOpenJDK();
    Foot foo = new Foot();
    new ByteBuddy().redefine(Bar.class).name(Foot.class.getName()).make()
        .load(Foot.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
    if (foo.m().trim().equals("bar")) {
      System.out.println("is true...");
      System.out.println("was modify value..." + foo.m());
      ClassReloadingStrategy classReloadStrategy = ClassReloadingStrategy.fromInstalledAgent();
      classReloadStrategy.reset(Foot.class);
      System.out.println("is origin..." + foo.m());
    } else {
      System.out.println("is false...");
    }
  }

}
