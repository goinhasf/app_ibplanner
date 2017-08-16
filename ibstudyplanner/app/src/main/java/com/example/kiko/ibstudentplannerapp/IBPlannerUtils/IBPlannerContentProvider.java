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

    public static final int CODE_SUBJECTS = 100;
    public static final int CODE_SUBJECT = 101;

    public static UriMatcher mUriMatcher = buildUriMatcher();


    @Override
    public boolean onCreate() {
        dbhelper = new IBDbHelper(getContext());
        return true;
    }

    private static UriMatcher buildUriMatcher(){
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(IBPlannerContract.AUTHORITY, IBPlannerContract.UserIBDataEntry.CONTENT_PATH, CODE_SUBJECTS);
        matcher.addURI(IBPlannerContract.AUTHORITY, IBPlannerContract.UserIBDataEntry.CONTENT_PATH + "/#", CODE_SUBJECT);

        return matcher;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        db = dbhelper.getReadableDatabase();
        switch (mUriMatcher.match(uri)) {
            case CODE_SUBJECTS:
                return db.query(IBPlannerContract.UserIBDataEntry.TABLE_NAME,
                    projection, selection, selectionArgs, null, null, sortOrder);
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

        Uri newUri = uri;

        switch (mUriMatcher.match(uri)) {
            case CODE_SUBJECTS:
            db.insert(IBPlannerContract.UserIBDataEntry.TABLE_NAME,
                    null, values);
            return newUri.buildUpon().appendPath(Integer.toString(values.getAsInteger(IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_NUMBER_COLUMN))).build();

            default:
                throw new IllegalArgumentException("URI does not exist: " + uri.toString());
        }
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        db = dbhelper.getWritableDatabase();
        int count = 0;
        switch (mUriMatcher.match(uri)){
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
        }
        return count;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (mUriMatcher.match(uri)) {
            case CODE_SUBJECTS:
                return db.delete(IBPlannerContract.UserIBDataEntry.TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("URI does not exist: " + uri.toString());
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (mUriMatcher.match(uri)) {
            case CODE_SUBJECTS:
                return db.update(IBPlannerContract.UserIBDataEntry.TABLE_NAME, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("URI does not exist: " + uri.toString());
        }
    }
}
