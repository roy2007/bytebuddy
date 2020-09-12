package org.extended.annotation;

@ExtendedCommandAnnotation
public class MyFoo {

  @ExtendedCommandAnnotation(annoPropEndTag = "12345")
  public String getStringTest(String rms) {
    return rms;
  }
}
