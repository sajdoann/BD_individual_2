package org.anna.algorithms;

public class LoopUnrollingMatrixMultiplication {

    public static double[][] multiply(double[][] a, double[][] b) {
        int n = a.length;
        double[][] result = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double sum = 0.0;

                // Loop unrolling for better performance
                int k = 0;
                int limit = n - n % 4; // Process 4 elements at a time
                for (; k < limit; k += 4) {
                    sum += a[i][k] * b[k][j]
                            + a[i][k + 1] * b[k + 1][j]
                            + a[i][k + 2] * b[k + 2][j]
                            + a[i][k + 3] * b[k + 3][j];
                }
                // Handle remaining elements
                for (; k < n; k++) {
                    sum += a[i][k] * b[k][j];
                }

                result[i][j] = sum;
            }
        }

        return result;
    }
}
