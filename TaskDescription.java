package com.rullion.task.example;

public enum TaskDescription {

    TASK1("EMPTY"),
    TASK2("a,b"),
    TASK3("a => b"),
    TASK4("a => b, c => d"),
    TASK5("a => b, b => c"),
    TASK6("ERROR");


    private String taskDescription;

    TaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * @return the taskDescription
     */
    public static String getTaskDescription(int task) {
        if (task == 1) {
            return TASK1.taskDescription;

        } else if (task == 2) {
            return TASK2.taskDescription;

        } else if (task == 3) {
            return TASK3.taskDescription;

        } else if (task == 4) {
            return TASK4.taskDescription;

        } else if (task == 5) {
            return TASK5.taskDescription;

        }

        return TASK6.taskDescription;
    }

}
