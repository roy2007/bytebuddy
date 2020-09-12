package org.foo;

import java.lang.reflect.Method;

import common.util.reflection.AnnotatedMethodCallback;
import common.util.reflection.ReflectionUtil;

public class AnnontationCallBackFoots {

  public static void main(String[] args) {
    Class<Foos> cls = Foos.class;
    try {
      ReflectionUtil.iterateAnnotatedMethods(new FooCallbacks(), cls, new AnnotatedMethodCallback() {
        @Override
        public void method(Object obj, Method method) throws Exception {
          System.out.println("22222222");
          method.invoke(obj);
        }
      });
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
