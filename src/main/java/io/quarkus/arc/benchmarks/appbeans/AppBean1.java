package io.quarkus.arc.benchmarks.appbeans;

import javax.enterprise.context.ApplicationScoped;
import io.quarkus.runtime.Startup;

@ApplicationScoped
@Startup
public class AppBean1 {
    void ping() {
    }
}