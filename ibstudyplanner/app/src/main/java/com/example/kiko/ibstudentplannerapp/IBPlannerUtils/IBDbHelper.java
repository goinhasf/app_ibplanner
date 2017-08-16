package com.example.kiko.ibstudentplannerapp.IBPlannerUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KIKO on 8/15/2017.
 */

public class IBDbHelper extends SQLiteOpenHelper {


    public IBDbHelper(Context context) {
        super(context,
                IBPlannerContract.UserIBDataEntry.TABLE_NAME,
                null,
                IBPlannerContract.UserIBDataEntry.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + IBPlannerContract.UserIBDataEntry.TABLE_NAME + " (" +
                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_NUMBER_COLUMN + " INTEGER NOT NULL PRIMARY KEY" +
                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_NAME_COLUMN + " TEXT NOT NULL, " +
                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_LEVEL_COLUMN + " TEXT NOT NULL " +
                IBPlannerContract.UserIBDataEntry._ID + " ASC");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP " + IBPlannerContract.UserIBDataEntry.TABLE_NAME + " IF EXISTS");
        onCreate(db);

    }


}
