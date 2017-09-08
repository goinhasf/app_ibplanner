package com.example.kiko.ibstudentplannerapp.IBPlannerUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.kiko.ibstudentplannerapp.IB.IBSubject;
import com.example.kiko.ibstudentplannerapp.MainActivity;

/**
 * Created by KIKO on 8/15/2017.
 */

public class IBPlannerContract {

    public static final String AUTHORITY = "com.example.kiko.ibstudentplannerapp";
    public static final String DATABASE_NAME = "ibplannerdb";
    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY).buildUpon().appendPath(DATABASE_NAME).build();
    public static final int DATABASE_VERSION = 3;


    public static final class UserIBDataEntry implements BaseColumns {

        public static final String CONTENT_PATH = "ibuserdata";
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(CONTENT_PATH).build();

        public static final String TABLE_NAME = "userIBDataEntry";

        public static final String IB_USERNAME_COLUMN = "user_name";

        public static final String IB_SUBJECT_GROUP_1_NAME_COLUMN = "subject_name_group_1";
        public static final String IB_SUBJECT_GROUP_2_NAME_COLUMN = "subject_name_group_2";
        public static final String IB_SUBJECT_GROUP_3_NAME_COLUMN = "subject_name_group_3";
        public static final String IB_SUBJECT_GROUP_4_NAME_COLUMN = "subject_name_group_4";
        public static final String IB_SUBJECT_GROUP_5_NAME_COLUMN = "subject_name_group_5";
        public static final String IB_SUBJECT_GROUP_6_NAME_COLUMN = "subject_name_group_6";

        public static final String IB_SUBJECT_GROUP_1_LEVEL_COLUMN = "subject_level_1";
        public static final String IB_SUBJECT_GROUP_2_LEVEL_COLUMN = "subject_level_2";
        public static final String IB_SUBJECT_GROUP_3_LEVEL_COLUMN = "subject_level_3";
        public static final String IB_SUBJECT_GROUP_4_LEVEL_COLUMN = "subject_level_4";
        public static final String IB_SUBJECT_GROUP_5_LEVEL_COLUMN = "subject_level_5";
        public static final String IB_SUBJECT_GROUP_6_LEVEL_COLUMN = "subject_level_6";


        public static Uri buildSubjectUri(IBSubject subject){
            return CONTENT_URI.buildUpon().appendPath("subject_group_number_"
                    + Integer.toString(subject.getGroup())).build();
        }


    }

    public static final class IBSubjectsTasksEntry implements BaseColumns {

        public static final int VERSION = 1;
        public static final String CONTENT_PATH = "ibsubjectdata";
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(CONTENT_PATH).build();

        // TODO 1. Add columns for new table.
        public static final String TABLE_NAME = "subjectdatatable";

        public static final String TASK_TYPE = "task_type";
        public static final String TASK_SUBJECT = "task_subject";
        public static final String TASK_DATE_DUE = "task_date_due";
        public static final String TASK_TIME_AT = "task_time_at";
        public static final String TASK_TIME = "task_time";


    }


}
