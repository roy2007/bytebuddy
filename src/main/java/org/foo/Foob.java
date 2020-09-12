package org.foo;

import org.agenttest.ToString;

@NBFoo
public class Foob {
  @NBFoo
  public String getString() {
    return "NBFOO";
  }

  @ToString
  public String toStringAgent() {
    return "NBFOO toString Agent!";
  }
}
