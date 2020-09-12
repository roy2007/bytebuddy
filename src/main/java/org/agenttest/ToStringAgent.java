package org.agenttest;

import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;
import static net.bytebuddy.matcher.ElementMatchers.named;

import java.lang.instrument.Instrumentation;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;

public class ToStringAgent {
  public static void premain(String arguments, Instrumentation instrumentation) {
    new AgentBuilder.Default().rebase(isAnnotatedWith(ToString.class)).transform(new AgentBuilder.Transformer() {
      @Override
      public DynamicType.Builder transform(DynamicType.Builder builder, TypeDescription typeDescription) {
        return builder.method(named("toString")).intercept(FixedValue.value("transformed"));
      }
    }).installOn(instrumentation);
  }

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {

  }
}
