package com.sparklesimply.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

}
