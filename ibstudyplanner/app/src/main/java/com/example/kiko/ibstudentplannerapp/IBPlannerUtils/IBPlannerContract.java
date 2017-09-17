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
    public static final int DATABASE_VERSION = 4;


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

        public static final String TASK_TYPE_1 = "task_type_1";
        public static final String TASK_TYPE_2 = "task_type_2";
        public static final String TASK_TYPE_3 = "task_type_3";
        public static final String TASK_TYPE_4 = "task_type_4";
        public static final String TASK_TYPE_5 = "task_type_5";
        public static final String TASK_TYPE_6 = "task_type_6";

        public static final String TASK_SUBJECT_1 = "task_subject_1";
        public static final String TASK_SUBJECT_2 = "task_subject_2";
        public static final String TASK_SUBJECT_3 = "task_subject_3";
        public static final String TASK_SUBJECT_4 = "task_subject_4";
        public static final String TASK_SUBJECT_5 = "task_subject_5";
        public static final String TASK_SUBJECT_6 = "task_subject_6";

        public static final String FK_TASK_SUBJECT_1 = "fk_task_subject_1";
        public static final String FK_TASK_SUBJECT_2 = "fk_task_subject_2";
        public static final String FK_TASK_SUBJECT_3 = "fk_task_subject_3";
        public static final String FK_TASK_SUBJECT_4 = "fk_task_subject_4";
        public static final String FK_TASK_SUBJECT_5 = "fk_task_subject_5";
        public static final String FK_TASK_SUBJECT_6 = "fk_task_subject_6";

        public static final String TASK_DATE_DUE_1 = "task_date_due_1";
        public static final String TASK_DATE_DUE_2 = "task_date_due_2";
        public static final String TASK_DATE_DUE_3 = "task_date_due_3";
        public static final String TASK_DATE_DUE_4 = "task_date_due_4";
        public static final String TASK_DATE_DUE_5 = "task_date_due_5";
        public static final String TASK_DATE_DUE_6 = "task_date_due_6";

        public static final String TASK_TIME_AT_1 = "task_time_at_1";
        public static final String TASK_TIME_AT_2 = "task_time_at_2";
        public static final String TASK_TIME_AT_3 = "task_time_at_3";
        public static final String TASK_TIME_AT_4 = "task_time_at_4";
        public static final String TASK_TIME_AT_5 = "task_time_at_5";
        public static final String TASK_TIME_AT_6 = "task_time_at_6";

        public static final String TASK_TIME_1 = "task_time_1";
        public static final String TASK_TIME_2 = "task_time_2";
        public static final String TASK_TIME_3 = "task_time_3";
        public static final String TASK_TIME_4 = "task_time_4";
        public static final String TASK_TIME_5 = "task_time_5";
        public static final String TASK_TIME_6 = "task_time_6";



    }


}
