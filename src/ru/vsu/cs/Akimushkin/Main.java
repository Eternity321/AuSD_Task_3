package ru.vsu.cs.Akimushkin;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main (String[] args) throws IllegalAccessException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormQueue().setVisible(true);
            }
        });

        /*MyQueue queue = new MyQueue();
        queue.pushHead(1);
        queue.pushHead(3);
        queue.pushHead(5);
        queue.pushHead(22);
        queue.pushHead(0);
        queue.pushHead(-5);
        queue.pushHead(5);
        queue.pushHead(5);
        queue.pushHead(23);
        queue.pushHead(-85);

        System.out.println(queue.sizeMinMax());
        //System.out.println(queue);*/
    }
}