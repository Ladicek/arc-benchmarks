package io.quarkus.arc.benchmarks;

import io.quarkus.arc.ArcInvocationContext;
import io.quarkus.arc.Reflectionless;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;

@MethodAccessingReflectionless
@Interceptor
@Reflectionless
@Priority(1)
public class MethodAccessingReflectionlessInterceptor {
    @AroundInvoke
    public Object aroundInvoke(ArcInvocationContext ctx) throws Exception {
        if (ctx.getMethodMetadata().isAnnotationPresent(MyAnnotation.class)
                && "bar".equals(ctx.getMethodMetadata().getAnnotation(MyAnnotation.class).value())) {
            return ctx.proceed();
        }
        return ctx.getMethodMetadata().getName();
    }
}
