package org.anna.algorithms;

public class StrassenMatrixMultiplication {

    public static double[][] multiply(double[][] a, double[][] b) {
        int n = a.length;
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

    // Strassen's Algorithm for Matrix Multiplication
    public static double[][] multiplyStrassen(double[][] a, double[][] b) {
        int n = a.length;

        // If matrix size is small, fall back on standard multiplication
        if (n <= 64) {
            return multiply(a, b);
        }

        // If size is not a power of two, pad matrices to the next power of two
        int newSize = (n % 2 == 0) ? n : n + 1;
        double[][] aPadded = padMatrix(a, newSize);
        double[][] bPadded = padMatrix(b, newSize);

        double[][] resultPadded = strassenRecursive(aPadded, bPadded, newSize);
        return unpadMatrix(resultPadded, n);
    }

    private static double[][] strassenRecursive(double[][] a, double[][] b, int size) {
        if (size == 1) {
            double[][] result = {{a[0][0] * b[0][0]}};
            return result;
        }

        int newSize = size / 2;
        double[][] a11 = new double[newSize][newSize];
        double[][] a12 = new double[newSize][newSize];
        double[][] a21 = new double[newSize][newSize];
        double[][] a22 = new double[newSize][newSize];
        double[][] b11 = new double[newSize][newSize];
        double[][] b12 = new double[newSize][newSize];
        double[][] b21 = new double[newSize][newSize];
        double[][] b22 = new double[newSize][newSize];

        splitMatrix(a, a11, a12, a21, a22);
        splitMatrix(b, b11, b12, b21, b22);

        double[][] m1 = strassenRecursive(add(a11, a22), add(b11, b22), newSize);
        double[][] m2 = strassenRecursive(add(a21, a22), b11, newSize);
        double[][] m3 = strassenRecursive(a11, subtract(b12, b22), newSize);
        double[][] m4 = strassenRecursive(a22, subtract(b21, b11), newSize);
        double[][] m5 = strassenRecursive(add(a11, a12), b22, newSize);
        double[][] m6 = strassenRecursive(subtract(a21, a11), add(b11, b12), newSize);
        double[][] m7 = strassenRecursive(subtract(a12, a22), add(b21, b22), newSize);

        double[][] c11 = add(subtract(add(m1, m4), m5), m7);
        double[][] c12 = add(m3, m5);
        double[][] c21 = add(m2, m4);
        double[][] c22 = add(subtract(add(m1, m3), m2), m6);

        return combineMatrices(c11, c12, c21, c22);
    }

    // Helper methods: Matrix addition, subtraction, split, and combine

    private static double[][] add(double[][] a, double[][] b) {
        int n = a.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    private static double[][] subtract(double[][] a, double[][] b) {
        int n = a.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    private static void splitMatrix(double[][] original, double[][] a11, double[][] a12, double[][] a21, double[][] a22) {
        int n = a11.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a11[i][j] = original[i][j];
                a12[i][j] = original[i][j + n];
                a21[i][j] = original[i + n][j];
                a22[i][j] = original[i + n][j + n];
            }
        }
    }

    private static double[][] combineMatrices(double[][] c11, double[][] c12, double[][] c21, double[][] c22) {
        int n = c11.length * 2;
        double[][] result = new double[n][n];
        int half = n / 2;
        for (int i = 0; i < half; i++) {
            for (int j = 0; j < half; j++) {
                result[i][j] = c11[i][j];
                result[i][j + half] = c12[i][j];
                result[i + half][j] = c21[i][j];
                result[i + half][j + half] = c22[i][j];
            }
        }
        return result;
    }

    private static double[][] padMatrix(double[][] matrix, int newSize) {
        double[][] padded = new double[newSize][newSize];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, padded[i], 0, matrix[i].length);
        }
        return padded;
    }

    private static double[][] unpadMatrix(double[][] matrix, int originalSize) {
        double[][] unpadded = new double[originalSize][originalSize];
        for (int i = 0; i < originalSize; i++) {
            System.arraycopy(matrix[i], 0, unpadded[i], 0, originalSize);
        }
        return unpadded;
    }
}
