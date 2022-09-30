package io.quarkus.arc.benchmarks;

import io.quarkus.arc.Arc;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
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
@Warmup(iterations = 5, time = 2, batchSize = 65536)
@Measurement(iterations = 5, time = 2, batchSize = 65536)
@State(Scope.Benchmark)
public class MethodAccessingInterceptionBenchmark {

    private MethodAccessingInterceptedBean bean;

    @Setup
    public void setup() {
        Arc.initialize();
        bean = Arc.container().instance(MethodAccessingInterceptedBean.class).get();
        if (bean == null) {
            throw new IllegalStateException("MethodAccessingInterceptedBean not found");
        }
    }

    @TearDown
    public void tearDown() {
        Arc.shutdown();
    }

    // avoid inlining the benchmark method into the JMH stub method,
    // because when the JVM deoptimizes the JMH stub method, it also
    // deoptimizes all the benchmarked code

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String baseline_noInterceptor() {
        return bean.baseline_noInterceptor();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String reflectiveWithoutAnnotation() {
        return bean.reflectiveWithoutAnnotation();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String reflectiveWithAnnotationShortCircuit() {
        return bean.reflectiveWithAnnotationShortCircuit();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String reflectiveWithAnnotationProceed() {
        return bean.reflectiveWithAnnotationProceed();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String reflectionlessWithoutAnnotation() {
        return bean.reflectionlessWithoutAnnotation();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String reflectionlessWithAnnotationShortCircuit() {
        return bean.reflectionlessWithAnnotationShortCircuit();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String reflectionlessWithAnnotationProceed() {
        return bean.reflectionlessWithAnnotationProceed();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String manyAnnotations_reflectiveWithoutAnnotation() {
        return bean.manyAnnotations_reflectiveWithoutAnnotation();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String manyAnnotations_reflectiveWithAnnotationShortCircuit() {
        return bean.manyAnnotations_reflectiveWithAnnotationShortCircuit();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String manyAnnotations_reflectiveWithAnnotationProceed() {
        return bean.manyAnnotations_reflectiveWithAnnotationProceed();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String manyAnnotations_reflectionlessWithoutAnnotation() {
        return bean.manyAnnotations_reflectionlessWithoutAnnotation();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String manyAnnotations_reflectionlessWithAnnotationShortCircuit() {
        return bean.manyAnnotations_reflectionlessWithAnnotationShortCircuit();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String manyAnnotations_reflectionlessWithAnnotationProceed() {
        return bean.manyAnnotations_reflectionlessWithAnnotationProceed();
    }
}
