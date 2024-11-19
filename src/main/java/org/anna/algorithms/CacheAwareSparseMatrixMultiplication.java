package org.anna.algorithms;

import org.anna.dataStructure.SparseMatrix;

import java.util.HashMap;
import java.util.Map;

public class CacheAwareSparseMatrixMultiplication {

    public static SparseMatrix multiply(SparseMatrix a, SparseMatrix b, int blockSize) {
        if (a.getCols() != b.getRows()) {
            throw new IllegalArgumentException("Matrix dimensions do not match for multiplication.");
        }

        SparseMatrix result = new SparseMatrix(a.getRows(), b.getCols());
        Map<Integer, Map<Integer, Double>> bCols = new HashMap<>();

        // Precompute column-major access for matrix b
        for (SparseMatrix.Element elem : b.getNonZeroElements()) {
            bCols.computeIfAbsent(elem.col, k -> new HashMap<>()).put(elem.row, elem.value);
        }

        // Perform multiplication with blocking
        for (int bi = 0; bi < a.getRows(); bi += blockSize) {
            for (int bj = 0; bj < b.getCols(); bj += blockSize) {
                for (SparseMatrix.Element elemA : a.getNonZeroElements()) {
                    if (elemA.row >= bi && elemA.row < bi + blockSize) {
                        if (bCols.containsKey(elemA.col)) {
                            for (Map.Entry<Integer, Double> elemB : bCols.get(elemA.col).entrySet()) {
                                int col = elemB.getKey();
                                if (col >= bj && col < bj + blockSize) {
                                    int row = elemA.row;
                                    double value = elemA.value * elemB.getValue();
                                    result.addElement(row, col, result.getElement(row, col) + value);
                                }
                            }
                        }
                    }
                }
            }
        }

        return result;
    }
}
