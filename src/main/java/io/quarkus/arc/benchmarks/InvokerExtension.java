package io.quarkus.arc.benchmarks;

import jakarta.enterprise.inject.build.compatible.spi.BeanInfo;
import jakarta.enterprise.inject.build.compatible.spi.BuildCompatibleExtension;
import jakarta.enterprise.inject.build.compatible.spi.InvokerInfo;
import jakarta.enterprise.inject.build.compatible.spi.Registration;
import jakarta.enterprise.inject.build.compatible.spi.Synthesis;
import jakarta.enterprise.inject.build.compatible.spi.SyntheticComponents;
import jakarta.enterprise.lang.model.declarations.MethodInfo;
import jakarta.inject.Singleton;

public class InvokerExtension implements BuildCompatibleExtension {
    private InvokerInfo directInvoker;
    private InvokerInfo transformingInvoker;
    private InvokerInfo transforming2Invoker;

    @Registration(types = InvokableBean.class)
    public void register(BeanInfo bean) {
        for (MethodInfo m : bean.invokableMethods()) {
            if ("ping".equals(m.name())) {
                directInvoker = bean.createInvoker(m).build();
                transformingInvoker = bean.createInvoker(m).setReturnValueTransformer(InvokableBean.class, "transform").build();
                transforming2Invoker = bean.createInvoker(m).setReturnValueTransformer(InvokableBean.Transform.class).build();
            }
        }
    }

    @Synthesis
    public void synthesis(SyntheticComponents synth) {
        synth.addBean(InvokerHolder.class)
                .scope(Singleton.class)
                .type(InvokerHolder.class)
                .withParam("directInvoker", directInvoker)
                .withParam("transformingInvoker", transformingInvoker)
                .withParam("transforming2Invoker", transforming2Invoker)
                .createWith(InvokerHolder.Creator.class);
    }
}
