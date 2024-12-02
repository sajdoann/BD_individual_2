package org.anna;


import org.anna.benchmark.MatrixBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws RunnerException {

        System.out.println("threads: " + (Runtime.getRuntime().availableProcessors() - 1));

         Options opt = new OptionsBuilder()
                .include(MatrixBenchmark.class.getSimpleName()) // Specify which benchmark to run
                .forks(1) // Run the benchmark in a separate JVM fork
                .build();

        new Runner(opt).run();


    }
}