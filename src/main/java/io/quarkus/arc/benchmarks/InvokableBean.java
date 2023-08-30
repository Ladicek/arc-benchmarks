package io.quarkus.arc.benchmarks;

import io.quarkus.arc.Unremovable;
import jakarta.enterprise.invoke.Invokable;
import jakarta.enterprise.invoke.Transformer;
import jakarta.inject.Singleton;
import org.openjdk.jmh.infra.Blackhole;

@Unremovable
@Singleton
public class InvokableBean {
    @Invokable
    public String ping(int param) {
        Blackhole.consumeCPU(500);
        return "ok";
    }

    public static String transform(String value) {
        Blackhole.consumeCPU(100);
        return value;
    }

    public static class Transform implements Transformer<String, String> {
        @Override
        public String transform(String value) {
            Blackhole.consumeCPU(100);
            return value;
        }
    }
}
