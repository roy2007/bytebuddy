package org.wj.bbdy;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    System.out.println("Before Class!");
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    System.out.println("After Class!");
  }

  @Test
  public void testHelloWorld() throws Exception {
    String className = getClass().getName();
    System.out.println("=================" + className + "====================");
    ClassLoader classloader = getClass().getClassLoader();
    Class<?> dynamicType = new ByteBuddy().subclass(Object.class).method(named("toString"))
        .intercept(FixedValue.value("Hello World!")).make().load(classloader, ClassLoadingStrategy.Default.WRAPPER)
        .getLoaded();
    assertThat(dynamicType.newInstance().toString(), is("Hello World!"));
  }
}
