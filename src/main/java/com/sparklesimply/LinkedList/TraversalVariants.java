package com.sparklesimply.linkedList;

import java.util.*;

public class TraversalVariants {

    /**
     * Problem statement: Given the head of a singly linked list and two integers
     * left and right where left <= right, reverse the nodes of the list from
     * position left to position right, and return the reversed list.
     * Time complexity: O(n)
     * 
     * @param head  node
     * @param left  position
     * @param right position
     * @return updated linked list
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right)
            return head;
        // dummy node to handle edge cases where head node is also part of to be
        // reversed sublist
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        // getting the prev point just before left position
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }
        ListNode curr = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode temp = curr.next;
            curr.next = temp.next;
            temp.next = prev.next;
            prev.next = temp;

        }
        return dummy.next;
    }

    /**
     * Problem statement: Given the head of a linked list, rotate the list to the
     * right by k places.
     * Time complexity: O(n)
     * 
     * @param head node
     * @param k    rotations
     * @return rotated list
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;
        int len = 1;
        ListNode temp = head;
        // calculating total length of linked list
        while (temp.next != null) {
            len++;
            temp = temp.next;
        }
        // making the linked list circular
        temp.next = head;
        // ensuring k is in bounds
        k = k % len;
        k = len - k;
        // moving k (len - k) steps
        while (k-- > 0) {
            temp = temp.next;
        }
        // updating head and removing circular link
        head = temp.next;
        temp.next = null;
        return head;
    }

    /**
     * Problem statement: Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
     * Time complexity: O(n)
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode dummyNode = new ListNode(0);
        ListNode curr = head;
        ListNode prev = dummyNode;
        while(curr != null && curr.next != null) {
            ListNode nextNode = curr.next;
            curr.next = nextNode.next;
            nextNode.next = curr;
            prev.next = nextNode;
            prev = curr;
            curr = curr.next;
        }
        return dummyNode.next;
    }

    /**
     * Problem statement: Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
     * Time complexity: O(n)
     * @param head node
     * @return true if linked list is palindrome
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)
            return true;
        ListNode mid = null, prevOfSlow = null, slow = head, fast = head;
        while(fast != null && fast.next != null) {
            prevOfSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null) {
            mid = slow;
            slow = slow.next;
            mid.next = null;
        }
        prevOfSlow.next = null;
        ListNode secondHalf = slow;
        ListNode reverseSecondHalf = reverseUtil(secondHalf);
        boolean isMatch = compareUtil(head, reverseSecondHalf);
        if(mid != null) {
            prevOfSlow.next = mid;
            mid.next = secondHalf;
        } else {
            prevOfSlow.next = secondHalf;
        }
        return isMatch;
    }
    ListNode reverseUtil(ListNode secondHalf) {
        ListNode prev = null, curr = secondHalf, next = null;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    boolean compareUtil(ListNode n1, ListNode n2) {
        ListNode t1 = n1, t2 = n2;
        while(t1 != null && t2 != null) {
            if(t1.val != t2.val)
                return false;
            t1 = t1.next;
            t2 = t2.next;
        }
        if(t1 == null && t2 == null)
            return true;
        return false;
    }

    /**
     * Problem statement: Given head, the head of a linked list, determine if the linked list has a cycle in it.
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
     * Return true if there is a cycle in the linked list. Otherwise, return false.
     * Time complexity: O(n)
     * @param head node
     * @return true if linked list has cycle
     */
    public boolean hasCycle(ListNode head) {
        if(head == null)
            return false;
        ListNode slow = head, fast = head;
        while(fast !=  null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast)
                return true;
        }
        return false;
    }

    /**
     * Problem statement: Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
     * Do not modify the linked list.
     * Time complexity: O(n)
     * @param head node
     * @return starting node of cycle in linked list
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null)
            return null;
        // using Floyd's cycle finding algorithm
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                break;
        }
        if(fast == null || fast.next == null)
            return null;
        // x: nodes before start of cycle
        // y: nodes part of cycle to node where slow and fast meet
        // c: nodes part of cycle
        // 2(x+y) - (x+y) = nc, diff of fast and slow travelled, n is number of cycles
        // x + y = nc, let slow move x steps and similarly head, node where they'll meet will be start of cycle
        ListNode temp = head;
        while(temp != slow) {
            temp = temp.next;
            slow = slow.next;
        }
        return temp;
    }

    /**
     * Problem statement: Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
     * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
     * You may not alter the values in the list's nodes, only nodes themselves may be changed.
     * Time complexity: O(n)
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 0)
            return head;
        return reverseKGroupUtil(head, k);
    }
    boolean checkNextKGroupPresent(ListNode head, int k) {
        ListNode curr = head;
        while(curr != null && k != 0) {
            curr = curr.next;
            k--;
        }
        return (k == 0);
    }
    ListNode reverseKGroupUtil(ListNode head, int k) {
        if(!checkNextKGroupPresent(head, k))
            return head;
        ListNode curr = head, prev = null, next = null;
        int count = 0;
        while(count < k && curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        if(next != prev) {
            head.next = reverseKGroupUtil(next, k);
        }
        return prev;
    }
}
