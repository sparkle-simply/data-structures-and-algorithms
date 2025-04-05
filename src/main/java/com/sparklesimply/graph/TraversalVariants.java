package com.sparklesimply.graph;

import java.util.*;

public class TraversalVariants {

    /**
     * BFS is breadth first traversal algorithm for tree or graph
     * This is used to traverse or search in tree or graph data structure.
     * It starts with root node for tree or arbitrary source vertex for graph and visits all nodes of current depth before moving to next depth level
     * Time complexity: O(V+E) all vertex are visited once and all edges are examined
     * @param adj adjacency list representation for graph
     * @param V total vertex count for graph
     * @param s arbitrary source vertex
     */
    public void bfs(ArrayList<ArrayList<Integer>> adj, int V, int s) {
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        while(!q.isEmpty()) {
            int v = q.poll();
            System.out.println(v + " ");
            for(int u : adj.get(v)) {
                if(!visited[u]) {
                    q.add(u);
                    visited[u] = true;
                }
            }
        }
    }

    /**
     * DFS is depth first traversal algorithm for tree or graph
     * This is used to traverse or search in tree or graph data structure.
     * It starts with root node for tree or arbitrary source vertex for graph and visits all nodes as far as possible before backtracking nodes
     * Time complexity: O(V+E)
     * @param adj adjacency list representation for graph
     * @param V total vertex count for graph
     * @param s arbitrary source vertex
     */
    public void dfs(ArrayList<ArrayList<Integer>> adj, int V, int s) {
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);
        dfsUtil(adj, s, visited);
    }
    private void dfsUtil(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited) {
        if(visited[s])
            return;
        visited[s] = true;
        System.out.println(s + " ");
        for (int u : adj.get(s)) {
            if (!visited[u])
                dfsUtil(adj, u, visited);
        }
    }

    /**
     * Topological sorting of direct acyclic graph is linear ordering vertices, such that if there is edge from vertex u to vertex v, vertex u will come before vertex v
     * Time complexity: O(V+E)
     * @param adj adjacency list representation for graph
     * @param V total vertex count for graph
     */
    public void topologicalSorting(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<V; i++) {
            if(!visited[i])
                topologicalSortingUtil(adj, i, visited, stack);
        }
        System.out.println("Sorted values:");
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
    private void topologicalSortingUtil(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited, Stack<Integer> stack) {
        if(visited[s])
            return;
        visited[s] = true;
        for(int u : adj.get(s)) {
            if(!visited[u])
                topologicalSortingUtil(adj, u, visited, stack);
        }
        stack.push(s);
    }

    /**
     * This method checks if cycle exists for directed graph.
     * To check this we track all the nodes that are visited till now in visited reference and all nodes that are being visited as part of current path in recursion stack reference
     * While traversing if we reach a node that is already present in recursion stack, we say graph is cyclic
     * Time complexity: O(V+E)
     * @param adj adjacency list representation for graph
     * @param V total vertex count for graph
     * @return true if cycle exists for directed graph
     */
    public boolean isCyclicDirectedGraph(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
        Arrays.fill(visited, false);
        Arrays.fill(recStack, false);
        for(int i=0; i<V; i++) {
            if(isCyclicDirectedGraphUtil(adj, i, visited, recStack))
                return true;
        }
        return false;
    }
    private boolean isCyclicDirectedGraphUtil(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited, boolean[] recStack) {
        if(recStack[s]) return true;
        if(visited[s]) return false;
        recStack[s] = true;
        visited[s] = true;
        for(int u : adj.get(s)) {
            if(isCyclicDirectedGraphUtil(adj, u, visited, recStack))
                return true;
        }
        recStack[s] = false;
        return false;
    }

    /**
     * This method checks if cycle exists for undirected graph
     * For visited vertex v, if we have adjacent vertex u such that u is already visited and parent of v is not u, then v say cycle exists in undirected graph
     * Time complexity: O(V+E)
     * @param adj adjacency list representation for graph
     * @param V total vertex count for graph
     * @return true if cycle exists for undirected graph
     */
    public boolean isCyclicUndirectedGraph(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);
        for(int i=0; i<V; i++) {
            if(!visited[i] && isCyclicUndirectedGraphUtil(adj, V, i, visited))
                return true;
        }
        return false;
    }
    private boolean isCyclicUndirectedGraphUtil(ArrayList<ArrayList<Integer>> adj, int V, int s, boolean[] visited) {
        int[] parent = new int[V];
        Arrays.fill(parent, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        while(!q.isEmpty()) {
            int v = q.poll();
            for(int u : adj.get(v)) {
                if(!visited[u]) {
                    q.add(u);
                    visited[u] = true;
                    parent[u] = v;
                } else if(parent[v] != u)
                    return true;
            }
        }
        return false;
    }

    /**
     * This method checks if provided graph is bipartite
     * A bipartite graph is a graph that can be divided into two disjoint sets such that every edge connects a vertex in one set to a vertex in other set
     * @param adj adjacency list representation for graph
     * @param V total vertex count for graph
     * @return true if graph is bipartite graph
     */
    public boolean isBipartite(ArrayList<ArrayList<Integer>> adj, int V) {
        int[] color = new int[V];
        Arrays.fill(color, -1);
        for(int i=0; i<V; i++) {
            if(isBipartiteUtil(adj, i, color))
                return true;
        }
        return false;
    }
    private boolean isBipartiteUtil(ArrayList<ArrayList<Integer>> adj, int s, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        color[s] = 1;
        while(!q.isEmpty()) {
            int v = q.poll();
            int clr = color[v] == 1 ? 2 : 1;
            for(int u : adj.get(v)) {
                if(color[v] == color[u])
                    return false;
                if(color[u] == -1) {
                    q.add(u);
                    color[u] = clr;
                }
            }
        }
        return true;
    }
}
