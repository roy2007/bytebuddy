package org.foo;

public class FooCallbacks {
  @Foos
  public void methodToCall() {
    System.out.println("annotations callback update........");
  }

  @Foos(url = "insert")
  public void anotherMethodToCall() {
    System.out.println("annotations callback insert........");
  }
}
