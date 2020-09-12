package org.extended.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author cf
 *
 */
public class UseMyFoo {

  public static void main(String[] args) {
    doOriginalImplementation(); // Prints "hello"
    doReflectionImplementation(); // Prints "hello"
  }

  public static void doOriginalImplementation() {
    MyFoo foo = new MyFoo();
    ExtendedCommandAnnotation e = foo.getClass().getAnnotation(ExtendedCommandAnnotation.class);
    String startTag = e.annoPropStartTag();
    System.out.println(startTag);
  }

  public static void doReflectionImplementation() {
    MyFoo foo = new MyFoo();

    Annotation[] annotations = foo.getClass().getAnnotations();
    // or the statement below, depends on what you intent to do:
    // Annotation[] annotations = foo.getClass().getDeclaredAnnotations();

    Class classOfExtendedCommandAnnotation = null;
    Annotation annotationOnClassFoo = null;
    for (Annotation a : annotations) {
      Class classA = a.annotationType();
      if (classA.getName().endsWith(".ExtendedCommandAnnotation")) {
        classOfExtendedCommandAnnotation = classA;
        annotationOnClassFoo = a;
        break;
      }
    }

    Method methodAnnoPropStartTag = null;
    if (classOfExtendedCommandAnnotation != null) {
      try {
        methodAnnoPropStartTag = classOfExtendedCommandAnnotation.getMethod("annoPropStartTag");
      } catch (NoSuchMethodException e) {
        throw new RuntimeException(e);
      }
    }

    if (methodAnnoPropStartTag != null) {
      try {
        String startTag = (String) methodAnnoPropStartTag.invoke(annotationOnClassFoo);
        System.out.println(startTag);
      } catch (ClassCastException e) {
        throw new RuntimeException(e);
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      } catch (IllegalArgumentException e) {
        throw new RuntimeException(e);
      } catch (InvocationTargetException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
