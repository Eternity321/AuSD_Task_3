package ru.vsu.cs.Akimushkin;

import java.util.ArrayList;

class JavaQueue implements MyQueueGeneric {
    private ArrayList<Integer> queue = new ArrayList<>();

    JavaQueue () {
    }

    public void pushHead(Integer value) {
        queue.add(0, value);
    }

    public Integer popTail() throws NullPointerException {
        int value = queue.get(queue.size()-1);
        queue.remove(queue.size()-1);
        return value;
    }

    public ArrayList<Integer> sizeMinMax() throws IllegalAccessException {
        ArrayList<Integer> result = new ArrayList<>();
        int size = queue.size();
        result.add(0, size);
        int curr = queue.get(0);
        int maxElement = curr;
        int minElement = curr;
        for (int i = 0; i < size; i++) {
            curr = queue.get(i);
            if (curr > maxElement) {
                maxElement = curr;
            }
            else if (curr < minElement) {
                minElement = curr;
            }
        }
        result.add(1, minElement);
        result.add(2, maxElement);
        return result;
    }
}