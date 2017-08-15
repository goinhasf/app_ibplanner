package com.example.kiko.ibstudentplannerapp.IBPlannerUtils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.kiko.ibstudentplannerapp.R;

import java.net.URI;

/**
 * Created by KIKO on 8/15/2017.
 */

public class IBPlannerContentProvider extends ContentProvider {

    private IBDbHelper dbhelper;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        dbhelper = new IBDbHelper(getContext());
        db = dbhelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor cursor = null;
        if (uri == IBPlannerContract.UserIBDataEntry.CONTENT_URI) {
            cursor = db.query(IBPlannerContract.UserIBDataEntry.TABLE_NAME,
                    projection, selection, selectionArgs, null, null, sortOrder);
        } else {
            throw new IllegalArgumentException("URI does not exist: " + uri.toString());
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri newUri;
        if (uri == IBPlannerContract.UserIBDataEntry.CONTENT_URI) {
            db.insert(IBPlannerContract.UserIBDataEntry.TABLE_NAME, null, values);
            newUri = uri.buildUpon().appendPath(values.getAsString(getContext()
                    .getString(R.string.ib_subject_key)))
                    .build();
            return newUri;
        } else {
            throw new IllegalArgumentException("URI does not exist: " + uri.toString());
        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        if (uri == IBPlannerContract.UserIBDataEntry.CONTENT_URI){
            return db.delete(IBPlannerContract.UserIBDataEntry.TABLE_NAME, selection, selectionArgs);
        } else {
            throw new IllegalArgumentException("URI does not exist: " + uri.toString());
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (uri == IBPlannerContract.UserIBDataEntry.CONTENT_URI){
            return db.update(IBPlannerContract.UserIBDataEntry.TABLE_NAME, values, selection, selectionArgs);
        } else {
            throw new IllegalArgumentException("URI does not exist: " + uri.toString());
        }
    }
}
