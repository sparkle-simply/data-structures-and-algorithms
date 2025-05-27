package com.sparklesimply.stack;

import java.util.*;

public class StackVariants {

    /**
     * Sort stack in ascending order such that bottom value is minimum and top value is maximum
     * Approach: using recursion, sorting stack elements in ascending order
     * Time complexity: O(n^2)
     * @param s
     */
    public void sortStackAsc(Stack<Integer> s) {
        if(!s.isEmpty()) {
            Integer temp = s.pop();
            sortStackAsc(s);
            sortedInsertAsc(s, temp);
        }
    }
    private void sortedInsertAsc(Stack<Integer> s, Integer x) {
        // pushing the minimum value first
        if(s.isEmpty() || s.peek() > x) {
            s.push(x);
            return;
        }
        Integer temp = s.pop();
        sortedInsertAsc(s, x);
        s.push(temp);
    }

    /**
     * Sort stack in descending order such that bottom value is maximum and top value is minimum
     * Approach: using recursion, sorting stack elements in descending order
     * Time complexity: O(n^2)
     * @param s
     */
    public void sortStackDesc(Stack<Integer> s) {
        if(!s.isEmpty()) {
            Integer temp = s.pop();
            sortStackDesc(s);
            sortedInsertDesc(s, temp);
        }
    }
    private void sortedInsertDesc(Stack<Integer> s, Integer x) {
        // pushing the maximum value first
        if(s.isEmpty() || s.peek() < x) {
            s.push(x);
            return;
        }
        Integer temp = s.pop();
        sortedInsertDesc(s, x);
        s.push(temp);
    }

}
