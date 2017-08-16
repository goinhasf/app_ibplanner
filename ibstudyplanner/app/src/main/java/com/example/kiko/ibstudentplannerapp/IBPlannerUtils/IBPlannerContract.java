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
    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY).buildUpon().build();


    public static final class UserIBDataEntry implements BaseColumns {

        public static final int VERSION = 1;
        public static final String CONTENT_PATH = "ibsubjectdata";
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(CONTENT_PATH).build();

        public static final String TABLE_NAME = "userIBDataEntry";
        public static final String IB_SUBJECT_GROUP_NUMBER_COLUMN = "subject_group_number";
        public static final String IB_SUBJECT_GROUP_NAME_COLUMN = "subject_name_group";
        public static final String IB_SUBJECT_GROUP_LEVEL_COLUMN = "subject_level";


        public static Uri buildSubjectUri(IBSubject subject){
            return CONTENT_URI.buildUpon().appendPath(Integer.toString(subject.getGroup())).build();
        }


    }


}
