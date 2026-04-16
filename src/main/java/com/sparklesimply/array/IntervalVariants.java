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

    /**
     * Problem statement: Given an array of intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
     * Approach: sort intervals on the basis of ending time, check if there is overlap with prev ending > curr starting, accordingly increment counter
     * Time complexity: O(n)
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1]-b[1]);
        int[] prev = intervals[0];
        int count = 0;
        for(int i=1; i<intervals.length; i++) {
            int[] curr = intervals[i];
            if(prev[1] > curr[0]) {
                count++;
            } else {
                prev = curr;
            }
        }
        return count;
    }

    /**
     * Problem statement: Ninja is the steel factory owner, and there are 'N' workers working at that factory.
     * Each worker has his own working time, and it is provided in the array 'intervals'
     * where 'INTERVALS[i][0]' gives the start time of the 'i'th worker and 'INTERVALS[i][1]' gives the end time of
     * the 'i'th worker. Ninja does not want to allow more than one worker to work at the same time,
     * so he needs your help to find the minimum number of workers he needs to reschedule their work time so as there
     * are non-overlapping working times in any two workers.
     * Time complexity: O(n)
     */
    public static int minimumReschedules(int n, int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> (i1[1] -i2[1]));
        int[] prev = intervals[0];
        int numberOfRechedules = 0;
        for(int i=1; i<intervals.length; i++) {
            int[] curr = intervals[i];
            if(prev[1] > curr[0]) {
                numberOfRechedules++;
            } else {
                prev = curr;
            }
        }
        return numberOfRechedules;
    }

    ArrayList<Interval> mergeIntervals(ArrayList<Interval> intervals) {
        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);
        ArrayList<Interval> result = new ArrayList<>();
        Interval prev = intervals.get(0);
        int n = intervals.size();
        for(int i=1; i<n; i++) {
            Interval curr = intervals.get(i);
            if(prev.end >= curr.start) {
                prev.end = Math.max(prev.end, curr.end);
            } else {
                result.add(prev);
                prev = curr;
            }
        }
        result.add(prev);
        return result;
    }

    public static int disjointIntervals(ArrayList<ArrayList<Integer>> arr, int n) {
        if(arr == null || arr.size() < 1)
            return 0;
        Collections.sort(arr, (a,b) -> Integer.compare(a.get(1),b.get(1)));
        int disjointCount = 1;
        int lastEndTime = arr.get(0).get(1);
        for(int i=1; i<arr.size(); i++) {
            int currStartTime = arr.get(i).get(0);
            if(currStartTime > lastEndTime) {
                disjointCount++;
                lastEndTime = arr.get(i).get(1);
            }
        }
        return disjointCount;
    }
}
