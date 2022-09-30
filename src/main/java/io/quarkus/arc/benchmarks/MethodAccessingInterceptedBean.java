package io.quarkus.arc.benchmarks;

import io.quarkus.arc.Unremovable;

import javax.enterprise.context.Dependent;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Unremovable
@Dependent
public class MethodAccessingInterceptedBean {
    public String baseline_noInterceptor() {
        return "hello";
    }

    @MethodAccessing
    public String reflectiveWithoutAnnotation() {
        return "hello";
    }

    @MethodAccessing
    @MyAnnotation("foo")
    public String reflectiveWithAnnotationShortCircuit() {
        return "hello";
    }

    @MethodAccessing
    @MyAnnotation("bar")
    public String reflectiveWithAnnotationProceed() {
        return "hello";
    }

    @MethodAccessingReflectionless
    public String reflectionlessWithoutAnnotation() {
        return "hello";
    }

    @MethodAccessingReflectionless
    @MyAnnotation("foo")
    public String reflectionlessWithAnnotationShortCircuit() {
        return "hello";
    }

    @MethodAccessingReflectionless
    @MyAnnotation("bar")
    public String reflectionlessWithAnnotationProceed() {
        return "hello";
    }

    @UselessAnnotation1
    @UselessAnnotation2
    @UselessAnnotation3
    @UselessAnnotation4
    @UselessAnnotation5
    @MethodAccessing
    public String manyAnnotations_reflectiveWithoutAnnotation() {
        return "hello";
    }

    @UselessAnnotation1
    @UselessAnnotation2
    @UselessAnnotation3
    @UselessAnnotation4
    @UselessAnnotation5
    @MethodAccessing
    @MyAnnotation("foo")
    public String manyAnnotations_reflectiveWithAnnotationShortCircuit() {
        return "hello";
    }

    @UselessAnnotation1
    @UselessAnnotation2
    @UselessAnnotation3
    @UselessAnnotation4
    @UselessAnnotation5
    @MethodAccessing
    @MyAnnotation("bar")
    public String manyAnnotations_reflectiveWithAnnotationProceed() {
        return "hello";
    }

    @UselessAnnotation1
    @UselessAnnotation2
    @UselessAnnotation3
    @UselessAnnotation4
    @UselessAnnotation5
    @MethodAccessingReflectionless
    public String manyAnnotations_reflectionlessWithoutAnnotation() {
        return "hello";
    }

    @UselessAnnotation1
    @UselessAnnotation2
    @UselessAnnotation3
    @UselessAnnotation4
    @UselessAnnotation5
    @MethodAccessingReflectionless
    @MyAnnotation("foo")
    public String manyAnnotations_reflectionlessWithAnnotationShortCircuit() {
        return "hello";
    }

    @UselessAnnotation1
    @UselessAnnotation2
    @UselessAnnotation3
    @UselessAnnotation4
    @UselessAnnotation5
    @MethodAccessingReflectionless
    @MyAnnotation("bar")
    public String manyAnnotations_reflectionlessWithAnnotationProceed() {
        return "hello";
    }

    @Target({ TYPE, METHOD })
    @Retention(RUNTIME)
    public @interface UselessAnnotation1 {
    }

    @Target({ TYPE, METHOD })
    @Retention(RUNTIME)
    public @interface UselessAnnotation2 {
    }

    @Target({ TYPE, METHOD })
    @Retention(RUNTIME)
    public @interface UselessAnnotation3 {
    }

    @Target({ TYPE, METHOD })
    @Retention(RUNTIME)
    public @interface UselessAnnotation4 {
    }

    @Target({ TYPE, METHOD })
    @Retention(RUNTIME)
    public @interface UselessAnnotation5 {
    }
}
