package org.wj.bbdy;

import static net.bytebuddy.matcher.ElementMatchers.named;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.dynamic.DynamicType.Unloaded;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    System.out.println("Hello World!");
    Class<?> dynamicType = new ByteBuddy().subclass(Object.class)// .method(named("toString"))
        // .intercept(FixedValue.value("Hello World!"))
        .make().load(App.class.getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER).getLoaded();
    try {
      System.out.println(dynamicType.newInstance().toString());
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

    Unloaded<Object> dynamicTyper = new ByteBuddy()
    // net.bytebuddy.dynamic.DynamicType.Unloaded<?> dynamicTyper = new ByteBuddy()
        .withNamingStrategy(new NamingStrategy() {
          @Override
          public String name(UnnamedType unnamedType) {
            return "i.love.ByteBuddy.Customer";
            // return "i.love.ByteBuddy." + unnamedType.getSuperClass().getSimpleName();
          }

          @Override
          public String toString() {
            return "Override to String";
          }
        }).subclass(Object.class).method(named("toString")).intercept(FixedValue.value("package selfdefin....."))
        // .name("example.Type")
        .make();
    try {
      System.out.println("212"
          + dynamicTyper.load(App.class.getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER).getLoaded()
              .newInstance().toString());
    } catch (InstantiationException | IllegalAccessException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    System.out.println(dynamicTyper);
    /*
     * try { Class<?> a =Class.forName("i.love.ByteBuddy.Customer"); a.newInstance().toString(); } catch
     * (ClassNotFoundException e) { // TODO Auto-generated catch block e.printStackTrace(); } catch
     * (InstantiationException e) { // TODO Auto-generated catch block e.printStackTrace(); } catch
     * (IllegalAccessException e) { // TODO Auto-generated catch block e.printStackTrace(); }
     */

  }
}
