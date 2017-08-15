package com.example.kiko.ibstudentplannerapp.IBPlannerUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.kiko.ibstudentplannerapp.MainActivity;

/**
 * Created by KIKO on 8/15/2017.
 */

public class IBPlannerContract {

    class UserIBDataEntry extends SQLiteOpenHelper implements BaseColumns {

        public static final int VERSION = 1;

        public static final String TABLE_NAME = "userIBDataEntry";
        public static final String IB_SUBJECT_GROUP_COLUMN = "group";
        public static final String IB_SUBJECT_NAME_COLUMN = "subject";
        public static final String IB_SUBJECT_LEVEL_COLUMN = "level";

        public UserIBDataEntry(Context context) {
            super(context, TABLE_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                    IB_SUBJECT_GROUP_COLUMN + " INTEGER NOT NULL PRIMARY KEY" +
                    IB_SUBJECT_NAME_COLUMN + " TEXT NOT NULL, " +
                    IB_SUBJECT_LEVEL_COLUMN + " TEXT NOT NULL");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP " + TABLE_NAME + " IF EXISTS");
            onCreate(db);

        }
    }
}
