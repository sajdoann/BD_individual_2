package org.anna.benchmark;

import org.anna.algorithms.MatrixMultiplication;
import org.anna.algorithms.StrassenMatrixMultiplication;
import org.openjdk.jmh.annotations.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class MatrixBenchmark {

    @Param({"8", "32", "64", "128", "256", "512"})
    private int n; // Matrix size

    private double[][] a;
    private double[][] b;

    @Setup(Level.Trial)
    public void setupMatrices() {
        a = new double[n][n];
        b = new double[n][n];

        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = random.nextDouble();
                b[i][j] = random.nextDouble();
            }
        }
    }

   @Benchmark
    public double[][] testMatrixMultiplication() {
        return MatrixMultiplication.multiply(a, b); // multiply or multiplyStrassen
    }

   /* @Benchmark
    public double[][] testMatrixMultiplicationStrassen() {
        return StrassenMatrixMultiplication.multiplyStrassen(a, b); // Strassen's algorithm
    }*/

}
