package org.foo;

import static net.bytebuddy.matcher.ElementMatchers.named;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.implementation.attribute.TypeAttributeAppender;

import org.agenttest.ToString;

public class AnnotationsProxyByByteBuddy {

  public static void main(String[] args) {
    try {
      Class<?> runtimeType = new ByteBuddy().withAttribute(TypeAttributeAppender.ForSuperType.INSTANCE)
          .withDefaultMethodAttributeAppender(MethodAttributeAppender.ForInstrumentedMethod.INSTANCE)
          .subclass(Foob.class).method(named("getString")).intercept(SuperMethodCall.INSTANCE)
          .method(named("toStringAgent")).intercept(FixedValue.value("Hello World!")).make().load(
          // ClassLoader.getSystemClassLoader(),
              AnnotationsProxyByByteBuddy.class.getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
          .getLoaded();
      System.out.println(runtimeType.toString());
      if (!runtimeType.equals(Foob.class)) {
        System.out.println("class is not Foo.class");
      }

      if (runtimeType.isAnnotationPresent(NBFoo.class)) {
        System.out.println("is NBFoo Annotation");
      }

      if (runtimeType.getDeclaredMethod("getString").isAnnotationPresent(NBFoo.class)
          || runtimeType.getDeclaredMethod("toStringAgent").isAnnotationPresent(ToString.class)) {
        System.out.println("method is NBFoo Annotation");
        for (Method m : runtimeType.getMethods()) {
          System.out.println(m.getName());
          if ("toStringAgent".equals(m.getName())) {
            try {
              m.invoke(runtimeType.newInstance());
            } catch (IllegalAccessException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            } catch (IllegalArgumentException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            } catch (InvocationTargetException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            } catch (InstantiationException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        }
      }
    } catch (NoSuchMethodException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
