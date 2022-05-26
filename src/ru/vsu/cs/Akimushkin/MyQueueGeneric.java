package ru.vsu.cs.Akimushkin;

import java.util.ArrayList;
import java.util.List;

interface MyQueueGeneric {
    void pushHead(Integer value);
    Integer popTail() throws IllegalAccessException;
    ArrayList<Integer> sizeMinMax() throws IllegalAccessException;
}

