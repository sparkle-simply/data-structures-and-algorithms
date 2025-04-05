package com.sparklesimply.queue;

import java.util.Arrays;
import java.util.PriorityQueue;

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
}
