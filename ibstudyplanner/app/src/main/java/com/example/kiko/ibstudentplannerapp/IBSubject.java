package com.example.kiko.ibstudentplannerapp;

/**
 * Created by KIKO on 8/15/2017.
 */

public class IBSubject {

    private String level;
    private int group;
    private String subjectName;

    public IBSubject(int group, String subjectName, String level){
        this.group = group;
        this.subjectName = subjectName;
        this.level = level;
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
