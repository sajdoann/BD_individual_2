package org.anna.dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SparseMatrix {
     private final int rows;
    private final int cols;
    private final Map<Integer, Map<Integer, Double>> elements; // Row -> (Column -> Value)

    public SparseMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.elements = new HashMap<>();
    }

    public void addElement(int row, int col, double value) {
        if (value != 0.0) {
            elements.computeIfAbsent(row, k -> new HashMap<>()).put(col, value);
        }
    }

    public double getElement(int row, int col) {
        // Get the row map, or default to an empty map
        Map<Integer, Double> rowMap = elements.getOrDefault(row, new HashMap<>());
        // Get the value from the row map, or default to 0.0
        return rowMap.getOrDefault(col, 0.0);
    }

    public Map<Integer, Map<Integer, Double>> getElements() {
        return elements;
    }

    // Provide an iterable structure for non-zero elements
    public Iterable<Element> getNonZeroElements() {
        List<Element> nonZeroElements = new ArrayList<>();
        for (Map.Entry<Integer, Map<Integer, Double>> rowEntry : elements.entrySet()) {
            int row = rowEntry.getKey();
            for (Map.Entry<Integer, Double> colEntry : rowEntry.getValue().entrySet()) {
                int col = colEntry.getKey();
                double value = colEntry.getValue();
                nonZeroElements.add(new Element(row, col, value));
            }
        }
        return nonZeroElements;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }


    public static class Element {
        public final int row;
        public final int col;
        public final double value;

        public Element(int row, int col, double value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
}

