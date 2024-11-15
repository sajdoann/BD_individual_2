package org.anna.algorithms;

public class MatrixMultiplication {

    public static double[][] multiply(double[][] a, double[][] b) {
        int n = a.length; // Assuming a is n x n
        double[][] c = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }
}
