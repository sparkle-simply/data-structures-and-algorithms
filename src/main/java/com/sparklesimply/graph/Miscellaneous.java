package com.sparklesimply.graph;

import java.util.*;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 */
public class Miscellaneous {

    /**
     * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company is the one with headID.
     * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1. Also, it is guaranteed that the subordination relationships have a tree structure.
     * The head of the company wants to inform all the company employees of an urgent piece of news. He will inform his direct subordinates, and they will inform their subordinates, and so on until all employees know about the urgent news.
     * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).
     * Return the number of minutes needed to inform all the employees about the urgent news.
     *
     * @param n
     * @param headID
     * @param manager
     * @param informTime
     * @return
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] graph = new List[n];
        for(int i=0; i<n; i++)
            graph[i] = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(manager[i] != -1)
                graph[manager[i]].add(i);
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{headID, 0});
        int result = 0;
        while(!q.isEmpty()) {
            int temp[] = q.poll();
            int currManager = temp[0];
            int currInformTime = temp[1];
            result = Math.max(result, currInformTime);
            for(int u : graph[currManager]) {
                q.offer( new int[]{u, currInformTime+informTime[currManager]});
            }
        }
        return result;
    }

    /**
     * Problem Statement: There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
     * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
     * Return true if you can finish all courses. Otherwise, return false.
     * Approach: observation is that courses follow order in which they can be finished, they can finish if there is no cycle present while tracking course ordering
     * We'll build the graph using prerequisites information, and then check if it is having cycle. If there is no cycle, then all courses can be successfully completed.
     * Time complexity: O(numCourses + prerequisites pairs)
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = Utility.buildGraph(numCourses, prerequisites);
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        Arrays.fill(visited, false);
        Arrays.fill(recStack, false);
        for(int i=0; i<numCourses; i++) {
            if(hasCycleUtil(graph, i, visited, recStack))
                return false;
        }
        return true;
    }
    boolean hasCycleUtil(List<List<Integer>> graph, int i, boolean[] visited, boolean[] recStack) {
        if(recStack[i]) return true;
        if(visited[i]) return false;
        visited[i] = recStack[i] = true;
        for(Integer neigh : graph.get(i)) {
            if(hasCycleUtil(graph, neigh, visited, recStack))
                return true;
        }
        recStack[i] = false;
        return false;
    }

    /**
     * Problem statement: There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
     * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
     * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
     * Approach: observation is that courses are to be linearly ordered in which they can be finished, there will be order if there is no cycle present while tracking course ordering
     * We'll build the graph using prerequisites information, and then check if it is having cycle and keep the track of nodes we are visiting in stack. If there is no cycle, then we'll have a valid linear order tracked in stack.
     * Time complexity: O(numCourses + prerequisites pairs)
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = Utility.buildGraph(numCourses, prerequisites);
        Stack<Integer> s = new Stack<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        Arrays.fill(visited, false);
        Arrays.fill(recStack, false);
        for(int i=0; i<numCourses; i++) {
            if(findOrderUtil(graph, i, visited, recStack, s)) {
                return new int[0]; // cycle present, no valid order
            }
        }
        int[] result = new int[numCourses];
        int i=0;
        while(!s.isEmpty()) {
            result[i++] = s.pop();
        }
        return result;
    }
    boolean findOrderUtil(List<List<Integer>> graph, int i, boolean[] visited, boolean[] recStack, Stack<Integer> s) {
        if(recStack[i]) return true;
        if(visited[i]) return false;

        recStack[i] = visited[i] = true;

        for(Integer neigh : graph.get(i)) {
            if(findOrderUtil(graph, neigh, visited, recStack, s))
                return true;
        }
        recStack[i] = false;
        s.push(i);
        return false;
    }

}
