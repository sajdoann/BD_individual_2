package org.anna.algorithms;

public class CacheOptimizedMatrixMultiplication {

    public static double[][] multiply(double[][] a, double[][] b) {
        int n = a.length;
        double[][] result = new double[n][n];
        double[][] bT = new double[n][n]; // Transposed matrix of b

        // Transpose matrix b
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bT[j][i] = b[i][j];
            }
        }

        // Perform multiplication using the transposed matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double sum = 0.0;
                for (int k = 0; k < n; k++) {
                    sum += a[i][k] * bT[j][k];
                }
                result[i][j] = sum;
            }
        }

        return result;
    }
}

