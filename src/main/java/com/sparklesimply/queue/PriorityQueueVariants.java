package com.sparklesimply.queue;

import java.util.*;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 */
public class PriorityQueueVariants {

    /**
     * This method returns the total number of events that can be attended
     * Reference <a href="https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/">Problem Statement</a>
     * @param events list of events with startDay and endDay
     * @return
     */
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0]-b[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int eventsCount = 0;
        int i = 0;
        int currDay = 0;
        while(i<events.length || !minHeap.isEmpty()) {
            if(minHeap.isEmpty()) {
                currDay = events[i][0];
            }
            // removing events that are completed before currDay
            while(!minHeap.isEmpty() && minHeap.peek() < currDay) {
                minHeap.poll();
            }

            while(i<events.length && events[i][0] == currDay) {
                minHeap.offer(events[i][1]);
                i++;
            }

            // attending event if possible
            if(!minHeap.isEmpty() && minHeap.peek() >= currDay) {
                eventsCount++;
                currDay++;
                minHeap.poll();
            }
        }
        return eventsCount;
    }

    /**
     * Given an integer array nums and an integer k, return the kth largest element in the array.
     * Note that it is the kth largest element in the sorted order, not the kth distinct element.
     * Can you solve it without sorting?
     * Time complexity: O(n log n) as insertion to minHeap is O(n log n)
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<k; i++) {
            pq.offer(nums[i]);
        }
        for(int i=k; i<nums.length; i++) {
            if(pq.peek() < nums[i]) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }
        return pq.poll();
    }

    /**
     * Problem statement: ou are given an array/list ‘ARR’ consisting of ‘N’ non - negative integers and an integer ‘K’. Your task is to return the K-th smallest element of the array.
     * For example:
     * Given an array/list ‘ARR' = [ 3, 2, 4, 5, 6 ] and 'K' = 3. The 3rd smallest element is "4" because the order of numbers is [ 2, 3, 4, 5, 6 ].
     * Time complexity: O(k)
     * @param v
     * @param n
     * @param k
     * @return
     */
    public int findkthSmallest(ArrayList<Integer> v, int n, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        for(int i=0; i<k; i++)
            pq.offer(v.get(i));
        for(int i=k; i<n; i++) {
            if(pq.peek() > v.get(i)) {
                pq.poll();
                pq.offer(v.get(i));
            }
        }
        return pq.poll();
    }

    /**
     * You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.
     * Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
     * Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.
     * Approach:
     * check for affordable capital and add the corresponding earned profit to total profit which is initially w
     * Time complexity: O(k log n + n log k)
     * @param k
     * @param w
     * @param profits
     * @param capital
     * @return
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        List<Project> project = new ArrayList<>();
        int n = profits.length;
        for(int i=0; i<n; i++) {
            project.add(new Project(profits[i], capital[i]));
        }
        Collections.sort(project, (a, b) -> a.capital - b.capital);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> y-x);
        int j=0;
        for(int i=0; i<k; i++) {
            while(j<n && project.get(j).capital <= w) {
                maxHeap.offer(project.get(j).profit);
                j++;
            }
            if(maxHeap.isEmpty())
                break;
            w += maxHeap.poll();
        }
        return w;
    }
    public class Project {
        public int profit;
        public int capital;
        public Project(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }
    }

    /**
     * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
     * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
     * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
     * Approach:
     * Use min heap functionality to get smallest sum and corresponding position of pairs which will get added to result list k times
     * Also to avoid duplicate pairs, track the position (custom string set value for simple tracking) that are fetched and added to result
     * Time complexity: O(k log k)
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        Set<String> tracker = new  HashSet<>();
        List<List<Integer>> result = new ArrayList<>();

        minHeap.offer(new int[]{nums1[0]+nums2[0], 0, 0});
        tracker.add("0,0");

        while(k-->0 && !minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int i = top[1];
            int j = top[2];
            result.add(List.of(nums1[i], nums2[j]));

            if(i+1<m && !tracker.contains((i+1)+","+j)) {
                minHeap.offer(new int[]{nums1[i+1]+nums2[j], i+1, j});
                tracker.add((i+1)+","+j);
            }

            if(j+1<n && !tracker.contains(i+","+(j+1))) {
                minHeap.offer(new int[]{nums1[i]+nums2[j+1], i, j+1});
                tracker.add(i+","+(j+1));
            }
        }
        return result;
    }
}
