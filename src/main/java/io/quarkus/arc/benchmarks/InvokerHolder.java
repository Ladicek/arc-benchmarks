package io.quarkus.arc.benchmarks;

import io.quarkus.arc.Unremovable;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.build.compatible.spi.Parameters;
import jakarta.enterprise.inject.build.compatible.spi.SyntheticBeanCreator;
import jakarta.enterprise.invoke.Invoker;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

public class InvokerHolder {
    final Invoker<InvokableBean, String> direct;
    final Invoker<InvokableBean, String> transforming;
    final Invoker<InvokableBean, String> transforming2;

    public InvokerHolder(Invoker<InvokableBean, String> direct, Invoker<InvokableBean, String> transforming, Invoker<InvokableBean, String> transforming2) {
        this.direct = direct;
        this.transforming = transforming;
        this.transforming2 = transforming2;
    }

    public static class Creator implements SyntheticBeanCreator<InvokerHolder> {
        @Override
        public InvokerHolder create(Instance<Object> lookup, Parameters parameters) {
            return new InvokerHolder(
                    parameters.get("directInvoker", Invoker.class),
                    parameters.get("transformingInvoker", Invoker.class),
                    parameters.get("transforming2Invoker", Invoker.class)
            );
        }
    }

    // only to make sure the bean `InvokerHolder` is not removed, otherwise unused
    @Singleton
    @Unremovable
    public static class Consumer {
        @Inject
        InvokerHolder holder;
    }
}
