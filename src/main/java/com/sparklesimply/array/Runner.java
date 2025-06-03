package com.sparklesimply.array;

public class Runner {
    private static boolean[][] relationships;

    public static boolean knows(int a, int b) {
        return relationships[a][b];
    }

    // Initialize relationships
    public static void setRelationships(boolean[][] matrix) {
        relationships = matrix;
    }
}
