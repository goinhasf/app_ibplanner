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
                IBPlannerContract.DATABASE_NAME,
                null,
                IBPlannerContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + IBPlannerContract.UserIBDataEntry.TABLE_NAME + " (" +
                IBPlannerContract.UserIBDataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                IBPlannerContract.UserIBDataEntry.IB_USERNAME_COLUMN + " TEXT NOT NULL, " +

                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_1_NAME_COLUMN + " TEXT NOT NULL, " +
                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_2_NAME_COLUMN + " TEXT NOT NULL, " +
                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_3_NAME_COLUMN + " TEXT NOT NULL, " +
                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_4_NAME_COLUMN + " TEXT NOT NULL, " +
                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_5_NAME_COLUMN + " TEXT NOT NULL, " +
                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_6_NAME_COLUMN + " TEXT NOT NULL, " +

                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_1_LEVEL_COLUMN + " TEXT NOT NULL, " +
                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_2_LEVEL_COLUMN + " TEXT NOT NULL, " +
                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_3_LEVEL_COLUMN + " TEXT NOT NULL, " +
                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_4_LEVEL_COLUMN + " TEXT NOT NULL, " +
                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_5_LEVEL_COLUMN + " TEXT NOT NULL, " +
                IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_6_LEVEL_COLUMN + " TEXT NOT NULL)");

        // TODO 2. Create SQL TABLE for Subject Data. Include FOREIGN KEY for each subject.
        db.execSQL("CREATE TABLE " + IBPlannerContract.IBSubjectsTasksEntry.TABLE_NAME + " (" +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_SUBJECT + " TEXT PRIMARY KEY NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TYPE + " TEXT NOT NULL, " +

                IBPlannerContract.IBSubjectsTasksEntry.TASK_DATE_DUE + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TIME_AT + " TEXT, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TIME + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + IBPlannerContract.UserIBDataEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + IBPlannerContract.IBSubjectsTasksEntry.TABLE_NAME);
        onCreate(db);

    }

    public void deleteDB(SQLiteDatabase db) {
        db.delete(IBPlannerContract.UserIBDataEntry.TABLE_NAME, null, null);
        db.delete(IBPlannerContract.IBSubjectsTasksEntry.TABLE_NAME, null, null);

    }


}
