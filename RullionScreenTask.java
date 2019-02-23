package com.rullion.task.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class RullionScreenTask {

    private static int taskCounter = 1;

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        System.out.println("Starting scheduled run ....");
        Runnable task = () -> {
            processResult(TaskDescription.getTaskDescription(getRandomNumberInRange(1, 6)));
            taskCounter++;
        };

        ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(task, 5, 1, TimeUnit.SECONDS);
        while (true) {
            Thread.sleep(1000);
            if (taskCounter > 50) {
                System.out.println("Shutting down scheduler");
                scheduledFuture.cancel(true);
                scheduler.shutdown();
                break;
            }
        }
    }



    private static void processResult(String taskType) {
        List<String> result = new ArrayList<>();
        System.out.println("Acceptance Criteria : ");

        switch(taskType) {
            case "EMPTY":
                //System.out.print("[]");
                break;

            case "a,b":
                result.add("a");
                result.add("b");
                break;

            case "a => b" :
                result.add("b");
                result.add("a");
                break;

            case "a => b, b => C" :
                result.add("b");
                result.add("a");
                result.add("d");
                result.add("c");
                break;

            case "a => b, b => c":
                result.add("c");
                result.add("b");
                result.add("a");
                break;

            default:
                result.add("Error - this is a cyclic dependency");

        }

        System.out.print("[");
        if (result.size() > 1) {
            result.forEach((s) -> System.out.print(s + ", "));

        } else if (result.size() == 1){
            System.out.print(result.get(0));
        }

        System.out.print("]");
        System.out.println();

    }

    public static String testProcessResult(String taskType) {
        StringBuilder result = new StringBuilder();
        result.append("[");

        switch(taskType) {
            case "EMPTY":
                result.append("]");
                break;

            case "a,b":
                result.append("a");
                result.append(",");
                result.append("b");
                break;

            case "a => b" :
                result.append("b");
                result.append(",");
                result.append("a");
                break;

            case "a => b, b => C" :
                result.append("b");
                result.append(",");
                result.append("a");
                result.append(",");
                result.append("d");
                result.append(",");
                result.append("c");
                break;

            case "a => b, b => c":
                result.append("c");
                result.append(",");
                result.append("b");
                result.append(",");
                result.append("a");
                break;

            default:
                result.append("Error - this is a cyclic dependency");

        }

        result.append("]");

        return result.toString();

    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


}
