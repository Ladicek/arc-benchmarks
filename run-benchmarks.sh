#!/bin/sh

VERSIONS="999-SNAPSHOT"

# Set max to use Runtime.getRuntime().availableProcessors()
THREADS="1"

# Benchmarks to run
if [ "$1" ]; then
    BENCHMARKS=$1
else
    BENCHMARKS="SingleInterceptorBenchmark|SingleDecoratorBenchmark|ReflectionsBenchmark|SubclassInstantiationBenchmark|ClientProxyInvocationBenchmark"
fi

echo "Versions: $VERSIONS"
echo "Benchmarks to run: $BENCHMARKS"

mvn clean

VERSIONS_ARRAY=$(echo $VERSIONS)

for i in $VERSIONS_ARRAY
do
  mvn package -Dquarkus.version=$i
  java -jar target/benchmarks.jar -t $THREADS -rf json -rff target/results-$i.json $BENCHMARKS
done;
