package com.example.kiko.ibstudentplannerapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class ChooseSubjectsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String HL = "HL";
    private static final String SL = "SL";

    private String[] subjectNames;
    private String[] subjectLevels;

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

    private String[] getSubjectNames() {
        String[] subjectNames = {
                mSubject1EditText.getText().toString(),
                mSubject2EditText.getText().toString(),
                mSubject3EditText.getText().toString(),
                mSubject4EditText.getText().toString(),
                mSubject5EditText.getText().toString(),
                mSubject6EditText.getText().toString(),
        };

        return subjectNames;
    }

    private String[] getSubjectLevels() {
        String[] subjectLevels = {
                checkedLevelOnRadioGroup(mSubject1RadioGroup),
                checkedLevelOnRadioGroup(mSubject2RadioGroup),
                checkedLevelOnRadioGroup(mSubject3RadioGroup),
                checkedLevelOnRadioGroup(mSubject4RadioGroup),
                checkedLevelOnRadioGroup(mSubject5RadioGroup),
                checkedLevelOnRadioGroup(mSubject6RadioGroup),
        };

        return subjectLevels;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_subjects);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createSubjectChooser();
        mContentView = findViewById(R.id.activity_chooser_layout);
        dbHelper = new IBDbHelper(this);
        db = dbHelper.getWritableDatabase();

        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                subjectNames = bundle.getStringArray(MainActivity.SUBJECTS_NAME_KEY);
                subjectLevels = bundle.getStringArray(MainActivity.SUBJECTS_LEVEL_KEY);

                if (subjectNames != null && subjectLevels != null) {
                    mSubject1EditText.setText(subjectNames[0]);
                    mSubject2EditText.setText(subjectNames[1]);
                    mSubject3EditText.setText(subjectNames[2]);
                    mSubject4EditText.setText(subjectNames[3]);
                    mSubject5EditText.setText(subjectNames[4]);
                    mSubject6EditText.setText(subjectNames[5]);
                    checkRadioButton(mSubject1RadioGroup, subjectLevels[0]);
                    checkRadioButton(mSubject2RadioGroup, subjectLevels[1]);
                    checkRadioButton(mSubject3RadioGroup, subjectLevels[2]);
                    checkRadioButton(mSubject4RadioGroup, subjectLevels[3]);
                    checkRadioButton(mSubject5RadioGroup, subjectLevels[4]);
                    checkRadioButton(mSubject6RadioGroup, subjectLevels[5]);
                } else {
                    throw new NullPointerException("Bundle data is null");
                }
            }


        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        if (itemID == android.R.id.home) {
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

    private String checkedLevelOnRadioGroup(RadioGroup radioGroup) {
        RadioButton checkedLevel = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        return checkedLevel.getText().toString();
    }

    private void checkRadioButton(RadioGroup group, String level){
        if (level.equals(HL)) {
            RadioButton button = (RadioButton) group.getChildAt(0);
            button.setChecked(true);
        } else if (level.equals(SL)){
            RadioButton button = (RadioButton) group.getChildAt(1);
            button.setChecked(true);
        } else {
            throw new IllegalArgumentException("Bad subject level data for RadioButtons. Level received " + level);
        }
    }

    private ContentValues getSubjectData() {
        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < 6; i++) {
            contentValues.put(MainActivity.subjectNameKEY[i], getSubjectNames()[i]);
            contentValues.put(MainActivity.subjectLevelKEY[i], getSubjectLevels()[i]);
            Log.d(getClass().getSimpleName(), getSubjectLevels()[i]);
        }


        return contentValues;


    }

    @Override
    public void onClick(View v) {
        int update = getContentResolver().update(IBPlannerContract.UserIBDataEntry.CONTENT_URI,getSubjectData(), null, null);
        Log.d(getClass().getSimpleName(), "Updated " + update);
        if (update == 0){
            ContentValues finalValues = getSubjectData();
            finalValues.put(IBPlannerContract.UserIBDataEntry.IB_USERNAME_COLUMN, MainActivity.username);
            getContentResolver().insert(IBPlannerContract.UserIBDataEntry.CONTENT_URI,finalValues);
        }
        Intent intent = new Intent(this, MainActivity.class);
        supportNavigateUpTo(intent);

    }

}
