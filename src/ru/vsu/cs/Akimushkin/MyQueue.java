package ru.vsu.cs.Akimushkin;

import java.util.ArrayList;

class MyQueue implements MyQueueGeneric {
    private ListNode head;
    private ListNode tail;

    public MyQueue() {
    }

    @Override
    public void pushHead(Integer value) {
        ListNode newHead = new ListNode(value);

        if (head != null) {
            newHead.setNextNode(head);
            head.setPrevNode(newHead);
            head = newHead;
        } else {
            head = newHead;
            tail = newHead;
        }
    }

    @Override
    public Integer popTail() throws IllegalAccessException {
        if (tail == null) {
            throw new IllegalAccessException();
        }

        Integer value = tail.getValue();

        if (tail.getPrevNode() != null) {
            tail = tail.getPrevNode();
            tail.setNextNode(null);
        }
        else {
            head = null;
            tail = null;
        }

        return value;
    }

    @Override
    public ArrayList<Integer> sizeMinMax() throws IllegalAccessException {
        pushHead(null);
        ArrayList<Integer> result = new ArrayList<>();
        Integer curr = popTail();
        int maxElement = curr;
        int minElement = curr;
        int size = 1;
        while (curr != null) {
            if (curr > maxElement) {
                maxElement = curr;
            }
            if (curr < minElement) {
                minElement = curr;
            }
            size++;
            pushHead(curr);
            curr = popTail();
        }
        result.add(size-1);
        result.add(minElement);
        result.add(maxElement);
        return result;
    }
}

