package com.sparklesimply.array;

import java.util.*;

public class IntervalVariants {

    /**
     * Problem statement: You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
     * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
     * Return intervals after the insertion.
     * Approach: update the result list of intervals along with merging overlap intervals with new interval insert action
     * Time complexity: O(n)
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();

        for(int[] curr : intervals) {
            if(newInterval[1] < curr[0]) {
                // if new interval is ending before curr interval
                result.add(newInterval);
                newInterval = curr;
            } else if(curr[1] < newInterval[0]) {
                // if curr interval is ending before new interval
                result.add(curr);
            } else {
                // if there is overlap
                newInterval[0] = Math.min(newInterval[0], curr[0]);
                newInterval[1] = Math.max(newInterval[1], curr[1]);
            }
        }
        // adding the last interval saved in new interval
        result.add(newInterval);
        return result.toArray(new int[result.size()][]);
    }
}
