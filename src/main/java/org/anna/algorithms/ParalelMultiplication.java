
package org.anna.algorithms;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class ParalelMultiplication {

    public static double[][] multiply(double[][] A, double[][] B, int numThreads) {
        ForkJoinPool customPool = new ForkJoinPool(numThreads);
        int n = A.length;
        double[][] result = new double[n][n];

        try {
            customPool.submit(() -> IntStream.range(0, n).parallel().forEach(i -> {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        result[i][j] += A[i][k] * B[k][j];
                    }
                }
            })).get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            customPool.shutdown();
        }

        return result;
    }
}
