package org.anna.benchmark;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class MyBenchmark {
    @Benchmark
    public int testMethod() {
        return myFunction(100);
    }

    public int myFunction(int n) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += i;
        }
        return result;
    }
}