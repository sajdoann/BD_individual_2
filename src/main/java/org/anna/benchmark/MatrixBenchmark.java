package org.anna.benchmark;

import org.anna.algorithms.*;

import org.anna.dataStructure.SparseMatrix;
import org.openjdk.jmh.annotations.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class MatrixBenchmark {

    // possible: any number
    @Param({ "8", "16", "32", "64"}) // "16", "32", "64"  "100", "128", donneee, "1000", "1024", "2000"
    private int n; // Matrix size

    //possible: "classic", "strassen", "sparse", "cache", "loop", "cachesparse"
    @Param({ "classic", "strassen", "sparse", "cache", "loop", "cachesparse"})
    private String algorithm;

    // possible: <0,1>
    @Param({  "0"}) //do "0", "0.5", "0.9" // Sparsity levels: 0.0 = dense, 0.5 = half zeros, 0.9 = mostly zeros
    private double sparsity;

     @Param({ "16"})
     private int blockSize;

     private double[][] denseA;
    private double[][] denseB;
    private SparseMatrix sparseA;
    private SparseMatrix sparseB;

    @Setup(Level.Trial)
    public void setupMatrices() {
         denseA = new double[n][n];
        denseB = new double[n][n];
        sparseA = new SparseMatrix(n, n);
        sparseB = new SparseMatrix(n, n);

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Populate dense matrices
                double valueA = random.nextDouble();
                double valueB = random.nextDouble();
                denseA[i][j] = (random.nextDouble() < sparsity) ? 0.0 : valueA;
                denseB[i][j] = (random.nextDouble() < sparsity) ? 0.0 : valueB;

                // Populate sparse matrices
                if (denseA[i][j] != 0.0) {
                    sparseA.addElement(i, j, denseA[i][j]);
                }
                if (denseB[i][j] != 0.0) {
                    sparseB.addElement(i, j, denseB[i][j]);
                }
            }
        }
    }

    @Benchmark
    public Object testMatrixMultiplication() {
         switch (algorithm.toLowerCase()) {
            case "classic":
                return MatrixMultiplication.multiply(denseA, denseB);
            case "strassen":
                return StrassenMatrixMultiplication.multiplyStrassen(denseA, denseB);
             case "cache":
                 return CacheOptimizedMatrixMultiplication.multiply(denseA, denseB);
             case "loop":
                 return LoopUnrollingMatrixMultiplication.multiply(denseA, denseB);
             case "sparse":
                return SparseMatrixMultiplication.multiply(sparseA, sparseB);
             case "cachesparse":
                 return CacheAwareSparseMatrixMultiplication.multiply(sparseA, sparseB ,blockSize);
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }
    }

}
