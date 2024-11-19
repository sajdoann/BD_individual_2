package org.anna.algorithms;

import org.anna.dataStructure.SparseMatrix;

import java.util.HashMap;
import java.util.Map;

public class SparseMatrixMultiplication {

    public static SparseMatrix multiply(SparseMatrix a, SparseMatrix b) {
        if (a.getCols() != b.getRows()) {
            throw new IllegalArgumentException("Matrix dimensions do not match for multiplication.");
        }

        SparseMatrix result = new SparseMatrix(a.getRows(), b.getCols());
        Map<Integer, Map<Integer, Double>> bCols = new HashMap<>();

        // Precompute column-major access for matrix b
        for (SparseMatrix.Element elem : b.getNonZeroElements()) {
            bCols.computeIfAbsent(elem.col, k -> new HashMap<>()).put(elem.row, elem.value);
        }

        // Perform multiplication
        for (SparseMatrix.Element elemA : a.getNonZeroElements()) {
            if (bCols.containsKey(elemA.col)) {
                for (Map.Entry<Integer, Double> elemB : bCols.get(elemA.col).entrySet()) {
                    int row = elemA.row;
                    int col = elemB.getKey();
                    double value = elemA.value * elemB.getValue();
                    result.addElement(row, col, result.getElement(row, col) + value);
                }
            }
        }

        return result;
    }
}


