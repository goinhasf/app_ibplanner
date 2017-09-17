package com.example.kiko.ibstudentplannerapp.IB;


import java.sql.Date;

/**
 * Created by djkik on 9/17/2017.
 */

public class IBTask {

    public static final int ASSIGNMENT_TASK_TYPE = 0;
    public static final int REVISION_TASK_TYPE = 0;

    private int taskType;
    private String subjectName;
    private String dateIssued;
    private String dateDue;
    private String taskDescription;


    public IBTask (String subjectName){
        this.subjectName = subjectName;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getDateDue() {
        return dateDue;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public class IBAssignment extends IBTask {

        private int progress = 0;

        public IBAssignment(String subjectName) {
            super(subjectName);
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public int calculateProgress() {
            Date timeIssued = Date.valueOf(dateIssued);
            Date timeDue = Date.valueOf(dateDue);

            long secondsIssued = timeIssued.getTime();
            long secondsDue = timeDue.getTime();

            int progress = (int) ((secondsIssued/secondsDue) * 100);

            return progress;

        }
    }

    public class IBRevision extends IBTask {

        int revisionTime;

        public IBRevision(String subjectName) {
            super(subjectName);
        }

        public int getRevisionTime() {
            return revisionTime;
        }

        public void setRevisionTime(int revisionTime) {
            this.revisionTime = revisionTime;
        }
    }
}
