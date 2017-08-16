package com.example.kiko.ibstudentplannerapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.kiko.ibstudentplannerapp.IB.IBSubject;
import com.example.kiko.ibstudentplannerapp.IBPlannerUtils.IBDbHelper;
import com.example.kiko.ibstudentplannerapp.IBPlannerUtils.IBPlannerContract;

import java.util.ArrayList;

public class ChooseSubjectsActivity extends AppCompatActivity implements View.OnClickListener{

    private View mContentView;

    private EditText mSubject1EditText;
    private EditText mSubject2EditText;
    private EditText mSubject3EditText;
    private EditText mSubject4EditText;
    private EditText mSubject5EditText;
    private EditText mSubject6EditText;

    private RadioGroup mSubject1RadioGroup;
    private RadioGroup mSubject2RadioGroup;
    private RadioGroup mSubject3RadioGroup;
    private RadioGroup mSubject4RadioGroup;
    private RadioGroup mSubject5RadioGroup;
    private RadioGroup mSubject6RadioGroup;

    private Button mAcceptButton;

    private SQLiteDatabase db;
    private IBDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subjects);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createSubjectChooser();
        mContentView = findViewById(R.id.activity_chooser_layout);
        dbHelper = new IBDbHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        if (itemID == android.R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            supportNavigateUpTo(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createSubjectChooser() {

        mSubject1EditText = (EditText) findViewById(R.id.et_subject_1);
        mSubject2EditText = (EditText) findViewById(R.id.et_subject_2);
        mSubject3EditText = (EditText) findViewById(R.id.et_subject_3);
        mSubject4EditText = (EditText) findViewById(R.id.et_subject_4);
        mSubject5EditText = (EditText) findViewById(R.id.et_subject_5);
        mSubject6EditText = (EditText) findViewById(R.id.et_subject_6);

        mSubject1RadioGroup = (RadioGroup) findViewById(R.id.rg_subject_level_1);
        mSubject2RadioGroup = (RadioGroup) findViewById(R.id.rg_subject_level_2);
        mSubject3RadioGroup = (RadioGroup) findViewById(R.id.rg_subject_level_3);
        mSubject4RadioGroup = (RadioGroup) findViewById(R.id.rg_subject_level_4);
        mSubject5RadioGroup = (RadioGroup) findViewById(R.id.rg_subject_level_5);
        mSubject6RadioGroup = (RadioGroup) findViewById(R.id.rg_subject_level_6);

        mAcceptButton = (Button) findViewById(R.id.bt_accept_button);
        mAcceptButton.setOnClickListener(this);


    }

    private String checkedLevelOnRadioGroup(RadioGroup radioGroup){
        RadioButton checkedLevel = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        return checkedLevel.getText().toString();
    }

    private ContentValues[] getSubjectData(){
        ContentValues[] contentValuesArray = new ContentValues[6];
        for (int i = 0; i < 6; i++){
            ContentValues contentValues = new ContentValues();
            LinearLayout layout = (LinearLayout) mContentView;
            LinearLayout subjectRow = (LinearLayout) layout.getChildAt(i);
            EditText editText = (EditText) subjectRow.getChildAt(0);
            String subjectName = editText.getText().toString();
            String level = checkedLevelOnRadioGroup((RadioGroup) subjectRow.getChildAt(1));

            contentValues.put(IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_NUMBER_COLUMN, i+1);
            contentValues.put(IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_NAME_COLUMN, subjectName);
            contentValues.put(IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_LEVEL_COLUMN, level);

            contentValuesArray[i] = contentValues;
        }

        return contentValuesArray;


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        getContentResolver().bulkInsert(IBPlannerContract.UserIBDataEntry.CONTENT_URI, getSubjectData());
        startActivity(intent);

    }


}
