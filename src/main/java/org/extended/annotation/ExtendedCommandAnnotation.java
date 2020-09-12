package org.extended.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExtendedCommandAnnotation {
  String annoPropUseTab() default "0";

  String annoPropStartTag() default "piffix";

  String annoPropEndTag() default "suffix";
}
