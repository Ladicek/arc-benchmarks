package io.quarkus.arc.benchmarks;

import io.quarkus.arc.Arc;
import jakarta.enterprise.invoke.Invoker;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.Throughput)
@Fork(5)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 5, time = 5)
@State(Scope.Benchmark)
public class InvokerBenchmark {

    private InvokableBean bean;

    private Invoker<InvokableBean, String> directInvoker;
    private Invoker<InvokableBean, String> transformingInvoker;
    private Invoker<InvokableBean, String> transforming2Invoker;

    @Setup
    public void setup() {
        Arc.initialize();
        bean = Arc.container().instance(InvokableBean.class).get();
        if (bean == null) {
            throw new IllegalStateException("InvokableBean not found");
        }
        InvokerHolder invokers = Arc.container().instance(InvokerHolder.class).get();
        if (invokers == null) {
            throw new IllegalStateException("Invokers not created properly");
        }
        directInvoker = invokers.direct;
        transformingInvoker = invokers.transforming;
        transforming2Invoker = invokers.transforming2;
    }

    @TearDown
    public void tearDown() {
        Arc.shutdown();
    }

    @Benchmark
    public String baseline() {
        return bean.ping(42);
    }

    @Benchmark
    public String directInvoker() {
        return directInvoker.invoke(bean, new Object[] {42});
    }

    @Benchmark
    public String transformingInvoker() {
        return transformingInvoker.invoke(bean, new Object[] {42});
    }

    @Benchmark
    public String transforming2Invoker() {
        return transforming2Invoker.invoke(bean, new Object[] {42});
    }
}
