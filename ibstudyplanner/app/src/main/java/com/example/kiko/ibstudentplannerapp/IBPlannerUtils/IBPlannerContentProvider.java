package com.example.kiko.ibstudentplannerapp.IBPlannerUtils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.kiko.ibstudentplannerapp.R;

import java.net.URI;

/**
 * Created by KIKO on 8/15/2017.
 */

public class IBPlannerContentProvider extends ContentProvider {

    private IBDbHelper dbhelper;
    private SQLiteDatabase db;

    //Matcher code value to get user subject data (From userIBDataEntry table)
    public static final int CODE_SUBJECTS = 100;

    //Matcher code value to get subject specific data (
    public static final int CODE_TASKS = 200;
    public static final int CODE_ASSIGNMENTS = 201;
    public static final int CODE_EXAMS = 202;


    public static UriMatcher mUriMatcher = buildUriMatcher();


    @Override
    public boolean onCreate() {
        dbhelper = new IBDbHelper(getContext());
        return true;
    }

    // TODO 3. Match code to new table
    private static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(IBPlannerContract.AUTHORITY, IBPlannerContract.DATABASE_NAME + "/" + IBPlannerContract.UserIBDataEntry.CONTENT_PATH, CODE_SUBJECTS);

        return matcher;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        db = dbhelper.getReadableDatabase();

        String sortingOrder;
        if (sortOrder == null) {
            sortingOrder = IBPlannerContract.UserIBDataEntry._ID + " ASC";
        } else {
            sortingOrder = sortOrder;
        }

        switch (mUriMatcher.match(uri)) {
            case CODE_SUBJECTS:
                return db.query(IBPlannerContract.UserIBDataEntry.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, sortingOrder);
            default:
                throw new IllegalArgumentException("URI does not exist: " + uri.toString());
        }

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db = dbhelper.getWritableDatabase();
        Uri newUri = uri;

        if (values == null) {
            throw new NullPointerException("values are empty");
        }
        switch (mUriMatcher.match(uri)) {
            case CODE_SUBJECTS:
                long id = db.insert(IBPlannerContract.UserIBDataEntry.TABLE_NAME, null, values);
                return Uri.withAppendedPath(uri, Long.toString(id));
            default:
                throw new IllegalArgumentException("URI does not exist: " + uri.toString());
        }

    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        db = dbhelper.getWritableDatabase();
        int count = 0;
        switch (mUriMatcher.match(uri)) {
            case CODE_SUBJECTS:
                db.beginTransaction();
                try {
                    for (ContentValues contentValues : values) {
                        long _id = db.insert(IBPlannerContract.UserIBDataEntry.TABLE_NAME, null, contentValues);
                        if (_id > 0)
                            count++;
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }

                return count;
            default:
                throw new IllegalArgumentException("URI does not exist: " + uri.toString());
        }



    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        db = dbhelper.getWritableDatabase();
        switch (mUriMatcher.match(uri)) {
            case CODE_SUBJECTS:
                return db.delete(IBPlannerContract.UserIBDataEntry.TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("URI does not exist: " + uri.toString());
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        db = dbhelper.getWritableDatabase();
        switch (mUriMatcher.match(uri)) {
            case CODE_SUBJECTS:
                return db.update(IBPlannerContract.UserIBDataEntry.TABLE_NAME, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("URI does not exist: " + uri.toString());
        }
    }
}
