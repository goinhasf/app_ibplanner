package com.example.kiko.ibstudentplannerapp.IBPlannerUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KIKO on 8/15/2017.
 */

public class IBDbHelper extends SQLiteOpenHelper {

    private static final String FOREIGN_KEY = "FOREIGN KEY";
    private static final String REFERENCES = "REFERENCES";
    private static final String CONSTRAINT = "CONSTRAINT";


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

                IBPlannerContract.IBSubjectsTasksEntry.TASK_SUBJECT_1 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_SUBJECT_2 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_SUBJECT_3 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_SUBJECT_4 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_SUBJECT_5 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_SUBJECT_6 + " TEXT NOT NULL, " +

                IBPlannerContract.IBSubjectsTasksEntry.TASK_TYPE_1 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TYPE_2 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TYPE_3 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TYPE_4 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TYPE_5 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TYPE_6 + " TEXT NOT NULL, " +


                IBPlannerContract.IBSubjectsTasksEntry.TASK_DATE_DUE_1 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_DATE_DUE_2 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_DATE_DUE_3 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_DATE_DUE_4 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_DATE_DUE_5 + " TEXT NOT NULL, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_DATE_DUE_6 + " TEXT NOT NULL, " +

                IBPlannerContract.IBSubjectsTasksEntry.TASK_TIME_AT_1 + " TEXT, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TIME_AT_2 + " TEXT, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TIME_AT_3 + " TEXT, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TIME_AT_4 + " TEXT, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TIME_AT_5 + " TEXT, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TIME_AT_6 + " TEXT, " +

                IBPlannerContract.IBSubjectsTasksEntry.TASK_TIME_1 + " TEXT, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TIME_2 + " TEXT, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TIME_3 + " TEXT, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TIME_4 + " TEXT, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TIME_5 + " TEXT, " +
                IBPlannerContract.IBSubjectsTasksEntry.TASK_TIME_6 + " TEXT, " +

                createForeignKey(IBPlannerContract.IBSubjectsTasksEntry.FK_TASK_SUBJECT_1,
                        IBPlannerContract.IBSubjectsTasksEntry.TASK_SUBJECT_1,
                        IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_1_NAME_COLUMN, true) +
                createForeignKey(IBPlannerContract.IBSubjectsTasksEntry.FK_TASK_SUBJECT_2,
                        IBPlannerContract.IBSubjectsTasksEntry.TASK_SUBJECT_2,
                        IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_2_NAME_COLUMN, true) +
                createForeignKey(IBPlannerContract.IBSubjectsTasksEntry.FK_TASK_SUBJECT_3,
                        IBPlannerContract.IBSubjectsTasksEntry.TASK_SUBJECT_3,
                        IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_3_NAME_COLUMN, true) +
                createForeignKey(IBPlannerContract.IBSubjectsTasksEntry.FK_TASK_SUBJECT_4,
                        IBPlannerContract.IBSubjectsTasksEntry.TASK_SUBJECT_4,
                        IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_4_NAME_COLUMN, true) +
                createForeignKey(IBPlannerContract.IBSubjectsTasksEntry.FK_TASK_SUBJECT_5,
                        IBPlannerContract.IBSubjectsTasksEntry.TASK_SUBJECT_5,
                        IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_5_NAME_COLUMN, true) +
                createForeignKey(IBPlannerContract.IBSubjectsTasksEntry.FK_TASK_SUBJECT_6,
                        IBPlannerContract.IBSubjectsTasksEntry.TASK_SUBJECT_6,
                        IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_6_NAME_COLUMN, false) +


                ")");
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

    private String createForeignKey(String constraint, String foreignKey, String reference, boolean appendMore) {

        String comma = ", ";
        String result = CONSTRAINT + "(" + constraint + ") " + FOREIGN_KEY + " (" + foreignKey + ") " + REFERENCES + " (" + reference + ")";

        if (appendMore){
            result += comma;
        }
        return result;
    }


}
