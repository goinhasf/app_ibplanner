package com.example.kiko.ibstudentplannerapp.IB;

import java.util.ArrayList;

/**
 * Created by KIKO on 8/15/2017.
 */

public class IBSubject {

    private String level;
    private int group;
    private String subjectName;
    private ArrayList<IBTask> tasks;

    public IBSubject(int group, String subjectName, String level){
        this.group = group;
        this.subjectName = subjectName;
        this.level = level;
        tasks = new ArrayList<>();
    }

    //Adds a new task to the ArrayList and returns an integer indicating the success
    // return 1 means success
    // return -1 means failure

    public int addTask(IBTask ibTask) {

        if (tasks != null) {
            tasks.add(ibTask);
            IBUser.taskNumber++;
            return 1;
        }

        return -1;
    }

    // Removes a task from the ArrayList. The rest is the same as the add method.

    public int deleteTask(IBTask ibTask) {

        if (IBUser.taskNumber != 0) {
            tasks.remove(ibTask);
            IBUser.taskNumber--;
            return 1;
        }

        return -1;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
