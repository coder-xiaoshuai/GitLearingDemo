package com.example.lib_java_test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueTest {

    public static void main(String[] args) {
//        Queue<Integer> queue = new LinkedList<>();
//        queue.offer(10);
//        queue.add(9);
//        printQueue(queue);
//
//        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
//        priorityQueue.add(20);
//        priorityQueue.add(19);
//        priorityQueue.add(21);
//        priorityQueue.add(18);
//        printQueue(priorityQueue);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.format(new Date());


        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date(System.currentTimeMillis()));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date(System.currentTimeMillis()-10000));
        System.out.println(calendar1.equals(calendar2));

        Calendar calendarLast = Calendar.getInstance();
        calendarLast.setTime(new Date(System.currentTimeMillis()));
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTimeInMillis(System.currentTimeMillis()-10000);
        //这里只要不是同一天就认为超过了一天
        System.out.println("是否超过了一天"+!(calendarLast.get(Calendar.YEAR) == calendarCurrent.get(Calendar.YEAR)
                && calendarLast.get(Calendar.MONTH) == calendarCurrent.get(Calendar.MONTH)
                && calendarLast.get(Calendar.DAY_OF_MONTH) == calendarCurrent.get(Calendar.DAY_OF_MONTH)));

    }

//    private static void printQueue(Queue queue) {
//        while (queue.peek() != null) {
//            System.out.println(queue.poll());
//        }
//    }



}
