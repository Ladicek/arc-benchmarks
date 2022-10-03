# ArC microbenchmarks

Collection of ArC microbenchmarks written using JMH.
Not peer reviewed or anything, so take the results with a grain of salt ;-)

## Running

Use `run-benchmarks.sh`.

## Running manually with Async Profiler

First, build the benchmark JAR: `mvn clean package`.
You will find it's a plain old JMH application, try e.g. `java -jar target/benchmarks.jar -h`.

Then, download Async Profiler from https://github.com/jvm-profiling-tools/async-profiler.
To get the 2.8.3 release for x86_64 Linux, you can run:

```bash
wget https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.8.3/async-profiler-2.8.3-linux-x64.tar.gz
tar xfz async-profiler-2.8.3-linux-x64.tar.gz
```

JMH has support for Async Profiler built in, you just have to make sure it can be found.
One easy way is to put the Async Profiler native library to the Java native library path using `-Djava.library.path`.

Also, there are some preparations necessary before running Async Profiler.
Please read https://github.com/jvm-profiling-tools/async-profiler/#basic-usage.

```bash
java -Djava.library.path=./async-profiler-2.8.3-linux-x64/build -jar target/benchmarks.jar -f 1 -prof async:output=flamegraph SingleInterceptorBenchmark
```

Here, we use `-f 1` to use only one JVM fork.
With N forks, we would unfortunately _not_ get N reports from Async Profiler, neither would we get a single combined report.
The last one would win, the previous are just lost.
