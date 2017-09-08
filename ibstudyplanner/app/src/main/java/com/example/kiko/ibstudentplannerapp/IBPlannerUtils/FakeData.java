package com.example.kiko.ibstudentplannerapp.IBPlannerUtils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.kiko.ibstudentplannerapp.IB.IBUser;
import com.example.kiko.ibstudentplannerapp.MainActivity;

/**
 * Created by djkik on 9/8/2017.
 */

public class FakeData {

    public static final String USERNAME = "kiko";
    private static final String[] IB_SUBJECTS = {
            "English",
            "Portuguese",
            "Maths",
            "Economics",
            "Physics",
            "Computer Science"
    };

    private static final String[] LEVELS = {
            "SL",
            "SL",
            "SL",
            "HL",
            "HL",
            "HL"
    };

    private static final String[] SUBJECT_NAME_KEY = {
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_1_NAME_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_2_NAME_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_3_NAME_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_4_NAME_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_5_NAME_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_6_NAME_COLUMN
    };

    private static final String[] SUBJECT_LEVEL_KEY = {
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_1_LEVEL_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_2_LEVEL_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_3_LEVEL_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_4_LEVEL_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_5_LEVEL_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_6_LEVEL_COLUMN
    };

    public static void deleteAllData(Context context) {
        IBDbHelper helper = new IBDbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        helper.deleteDB(db);
    }

    public static void generateFakeUserData(Context context) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(IBPlannerContract.UserIBDataEntry.IB_USERNAME_COLUMN, USERNAME);

        for (int i = 0; i < 6; i++){
            contentValues.put(SUBJECT_NAME_KEY[i],IB_SUBJECTS[i]);
            contentValues.put(SUBJECT_LEVEL_KEY[i], LEVELS[i]);

        }

        context.getContentResolver().insert(IBPlannerContract.UserIBDataEntry.CONTENT_URI, contentValues);
    }
}
