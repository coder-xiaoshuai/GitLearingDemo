package com.example.zhangshuai.test;

import java.util.LinkedList;
import java.util.Queue;

public class Test06 {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(10);
        queue.add(9);
        printQueue(queue);
    }

    private static void printQueue(Queue queue) {
        while (queue.peek() != null) {
            System.out.println(queue.poll());
        }
    }
}
