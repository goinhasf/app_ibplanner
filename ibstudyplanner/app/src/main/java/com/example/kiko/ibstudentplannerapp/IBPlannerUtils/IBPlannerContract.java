package com.example.kiko.ibstudentplannerapp.IBPlannerUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.kiko.ibstudentplannerapp.MainActivity;

/**
 * Created by KIKO on 8/15/2017.
 */

public class IBPlannerContract {

    public static final String AUTHORITY = "com.example.kiko.ibstudentplannerapp.IBPlannerContentProvider";
    public static final Uri BASE_URI = Uri.parse("content:// " + AUTHORITY + "/").buildUpon().build();


    public static class UserIBDataEntry implements BaseColumns {

        public static final int VERSION = 1;
        public static final String CONTENT = "ibsubjectdata";
        public static Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(CONTENT + "/").build();

        public static final String TABLE_NAME = "userIBDataEntry";
        public static final String IB_SUBJECT_GROUP_COLUMN = "group";
        public static final String IB_SUBJECT_NAME_COLUMN = "subject";
        public static final String IB_SUBJECT_LEVEL_COLUMN = "level";


    }
}
