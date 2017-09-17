package com.example.kiko.ibstudentplannerapp.IB;

import java.util.ArrayList;

/**
 * Created by KIKO on 8/15/2017.
 */

public class IBUser {

    //The name of the user
    private String name;
    /* This array will store IBSubjects based on where in the group they are categorized. For example English
    Lang & Lit is a Group 1 Subject so it will be stored on the first index of this array */
    private IBSubject[] userIBSubjects;

    public static int taskNumber;

    public IBUser() {
    }

    public IBUser(String name){
        this.name = name;
        userIBSubjects  = new IBSubject[6];
    }


    public void addIBSubject(IBSubject newIBSubject){
        int group = newIBSubject.getGroup();
        userIBSubjects[group-1] = newIBSubject;
    }

    private boolean isUserIBSubjectsEmpty(){

        for (IBSubject subject : userIBSubjects){
            if (subject != null)
                return false;
        }

        return true;
    }

    public ArrayList<IBTask> getAllActiveTasks() {

        for (IBSubject subject : userIBSubjects) {

        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserIBSubjects(IBSubject[] userIBSubjects) {
        this.userIBSubjects = userIBSubjects;
    }

    public IBSubject[] getUserIBSubjects() {
        return userIBSubjects;
    }
}
