package com.sparklesimply.graph;

import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static List<List<Integer>> buildGraph(int numCourses, int[][] pre) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<numCourses; i++)
            graph.add(new ArrayList<Integer>());
        for(int[] pair : pre) {
            int u = pair[0];
            int v = pair[1];
            graph.get(v).add(u);
        }
        return graph;
    }
}
