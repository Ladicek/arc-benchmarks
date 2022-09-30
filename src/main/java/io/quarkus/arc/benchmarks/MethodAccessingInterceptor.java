package io.quarkus.arc.benchmarks;

import io.quarkus.arc.ArcInvocationContext;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;

@MethodAccessing
@Interceptor
@Priority(1)
public class MethodAccessingInterceptor {
    @AroundInvoke
    public Object aroundInvoke(ArcInvocationContext ctx) throws Exception {
        if (ctx.getMethod().isAnnotationPresent(MyAnnotation.class)
                && "bar".equals(ctx.getMethod().getAnnotation(MyAnnotation.class).value())) {
            return ctx.proceed();
        }
        return ctx.getMethod().getName();
    }
}
