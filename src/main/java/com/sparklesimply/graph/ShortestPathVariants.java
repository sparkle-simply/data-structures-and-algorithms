package com.sparklesimply.graph;

import java.util.Arrays;

public class ShortestPathVariants {

    /**
     * Dijktras algorithm is used to get the shorted distance of all the vertices from provided source vertex
     * Here, we track two set, one set captures vertices that are part of shorted path and the other captures vertices that are not yet included in shortest path set
     * For calculating shortest distance, at every step in algorithm, we find the vertex that is not yet included in shortest path set and has the minimum distance from source vertex
     * Time complexity O(V^2)
     * @param graph matrix representation of graph
     * @param V total vertex count for graph
     * @param s source vertex
     */
    public void dijktras(int[][] graph, int V, int s) {
        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(sptSet, false);
        dist[s] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = getMinDistance(dist, sptSet, V);
            sptSet[u] = true;
            for (int v : graph[u]) {
                if ((!sptSet[v]) && (graph[u][v] != 0) && (dist[u] != Integer.MAX_VALUE) && (dist[u] + graph[u][v] < dist[v]))
                    dist[v] = dist[u] + graph[u][v];
            }
        }
        System.out.println("Shortest distance of all vertices from vertex: " + s);
        for (int i = 0; i < V; i++) {
            System.out.print("vertex " + i + ":" + dist[i] + " ");
        }
    }
    private int getMinDistance(int[] dist, boolean[] sptSet, int V) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < V; i++) {
            if (!sptSet[i] && dist[i] < minDistance) {
                minDistance = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
